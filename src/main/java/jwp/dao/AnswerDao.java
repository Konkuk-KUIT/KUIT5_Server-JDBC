package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Answer;

import java.util.List;

public class AnswerDao {
    private final JdbcTemplate<Answer> jdbcTemplate = new JdbcTemplate<>();

    public List<Answer> read(Long questionId) {
        String sql = "SELECT * FROM ANSWERS WHERE questionId = ?";

        PreparedStatementSetter pstmtSetter = pstmt ->
                pstmt.setLong(1, questionId);

        RowMapper<Answer> rowMapper = rs -> new Answer(rs.getLong("answerId"),
                rs.getString("writer"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate"),
                rs.getLong("questionId"));

        return jdbcTemplate.query(sql, pstmtSetter, rowMapper);
    }

    public Answer insert(Answer answer) {
        KeyHolder keyHolder = new KeyHolder();

        String sql = "INSERT INTO ANSWERS (writer, contents, createdDate, questionId) VALUES (?, ?, ?, ?)";

        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, answer.getWriter());
            pstmt.setString(2, answer.getContents());
            pstmt.setTimestamp(3, answer.getCreatedDate());
            pstmt.setLong(4, answer.getQuestionId());
        };

        jdbcTemplate.update(sql, pstmtSetter, keyHolder);

        return findAnswerById(keyHolder.getId());
    }

    public Answer findAnswerById(Long answerId) {
        String sql = "SELECT answerId, writer, contents, createdDate, questionId FROM ANSWERS WHERE answerId = ?";

        PreparedStatementSetter pstmtSetter = pstmt ->
            pstmt.setLong(1, answerId);

        RowMapper<Answer> rowMapper = rs -> new Answer(rs.getLong("answerId"),
                rs.getString("writer"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate"),
                rs.getLong("questionId"));

        return jdbcTemplate.queryForObject(sql, pstmtSetter, rowMapper);
    }

}
