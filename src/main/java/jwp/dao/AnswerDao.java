package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import java.sql.SQLException;
import java.util.List;
import jwp.model.Answer;

public class AnswerDao {
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public List<Answer> findAllByQuestionId(Long questionId) throws SQLException {
        String sql = "SELECT * FROM ANSWERS WHERE questionId = ?";
        return jdbcTemplate.query(sql,
                pstmt -> pstmt.setLong(1, questionId),
                rs -> new Answer(
                        rs.getLong("answerId"),
                        rs.getLong("questionId"),
                        rs.getString("writer"),
                        rs.getString("contents"),
                        rs.getTimestamp("createdDate")
                )
        );
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


        jdbcTemplate.updateWithKeyholder(sql, pstmtSetter, keyHolder);
        return findByAnswerId(keyHolder.getId());
    }

    public Answer findByAnswerId(long answerId) throws SQLException {
        String sql = "SELECT answerId, writer, contents, createdDate, questionId " +
                "FROM ANSWERS WHERE answerId=?";

        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setLong(1, answerId);
        };

        RowMapper rowMapper = rs -> new Answer(rs.getLong("answerId"),
                rs.getLong("questionId"),
                rs.getString("writer"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate"));

        return (Answer) jdbcTemplate.queryForObject(sql, pstmtSetter, rowMapper);
    }
}