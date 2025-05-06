package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import java.sql.SQLException;
import java.util.List;
import jwp.model.Answer;

public class AnswerDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public List<Answer> findAllByQuestionId(int questionId) {
        String sql = "SELECT * FROM ANSWERS WHERE questionId=? ORDER BY answerId";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setInt(1, questionId);
        };
        RowMapper<Answer> rowMapper = rs -> new Answer(rs.getInt("answerId"),
                rs.getInt("questionId"),
                rs.getString("writer"),
                rs.getString("contents"),
                rs.getDate("createdDate"));
        return jdbcTemplate.query(sql, pstmtSetter, rowMapper);
    }

    public Answer insert(Answer answer) throws SQLException {
        KeyHolder keyHolder = new KeyHolder();
        String sql = "INSERT INTO ANSWERS (writer, contents, createdDate, questionId) VALUES (?, ?, ?, ?)";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, answer.getWriter());
            pstmt.setString(2, answer.getContents());
            pstmt.setObject(3, answer.getCreatedDate());
            pstmt.setObject(4, answer.getQuestionId());
        };
        jdbcTemplate.update(sql, pstmtSetter, keyHolder);
        return findByAnswerId(keyHolder.getId());
    }

    public Answer findByAnswerId(int answerId) throws SQLException {
        String sql = "SELECT answerId, writer, contents, createdDate, questionId " +
                "FROM ANSWERS WHERE answerId=?";

        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setInt(1, answerId);
        };

        RowMapper<Answer> rowMapper = rs -> new Answer(rs.getInt("answerId"),
                rs.getInt("questionId"),
                rs.getString("writer"),
                rs.getString("contents"),
                rs.getDate("createdDate"));

        return jdbcTemplate.queryForObject(sql, pstmtSetter, rowMapper);
    }
}
