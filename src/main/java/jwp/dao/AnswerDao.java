package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Answer;

import java.util.List;

public class AnswerDao {

    public List<Answer> findAllByQuestionId(Long questionId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "SELECT * FROM Answers WHERE questionId = ?";

        PreparedStatementSetter preparedStatementSetter = preparedStatement ->
                preparedStatement.setString(1, String.valueOf(questionId));

        RowMapper<Answer> rowMapper = resultSet -> new Answer(
                resultSet.getLong("answerId"),
                resultSet.getString("writer"),
                resultSet.getString("contents"),
                resultSet.getTimestamp("createdDate").toLocalDateTime(),
                resultSet.getInt("questionId")
        );
        return jdbcTemplate.query(sql, preparedStatementSetter, rowMapper);
    }
}
