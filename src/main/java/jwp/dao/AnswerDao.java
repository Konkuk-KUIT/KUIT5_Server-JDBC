package jwp.dao;

import core.jdbc.JdbcTemplate;
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

}
