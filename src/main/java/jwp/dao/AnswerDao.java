package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Answer;

import java.util.List;

public class AnswerDao {
    public List<Answer> findByQuestionId(long questionId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT * FROM Answers WHERE questionId = ?";
        PreparedStatementSetter preparedStatementSetter = preparedStatement -> preparedStatement.setLong(1, questionId);

        RowMapper<Answer> rowMapper = resultSet -> new Answer(
                resultSet.getLong("answerId"),
                resultSet.getString("writer"),
                resultSet.getString("contents"),
                resultSet.getTimestamp("createdDate"),
                resultSet.getLong("questionId")
        );

        return jdbcTemplate.query(sql, preparedStatementSetter, rowMapper);
    }
}
