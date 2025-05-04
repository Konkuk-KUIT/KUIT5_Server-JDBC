package jwp.dao;

import core.jdbc.JdbcTemplate;
import jwp.model.Answer;

import java.util.List;

public class AnswerDao {

    public List<Answer> findAllByQuestionId(Long questionId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "SELECT * FROM Answers WHERE questionId = ?";

        return jdbcTemplate.query(sql, preparedStatement ->
                preparedStatement.setString(1, String.valueOf(questionId)),
                resultSet -> new Answer(
                resultSet.getLong("answerId"),
                resultSet.getString("writer"),
                resultSet.getString("contents"),
                resultSet.getTimestamp("createdDate").toLocalDateTime(),
                resultSet.getInt("questionId")));
    }
}