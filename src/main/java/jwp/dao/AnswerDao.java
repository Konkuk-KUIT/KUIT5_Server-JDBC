package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Answer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
}
