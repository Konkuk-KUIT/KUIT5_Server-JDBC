package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Answer;

import java.util.List;

public class AnswerDao {

    public Answer insert(Answer answer) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        KeyHolder keyHolder = new KeyHolder();

        String sql = "INSERT INTO Answers (writer, contents, createdDate, questionId) VALUES (?, ?, ?, ?)";

        PreparedStatementSetter preparedStatementSetter = preparedStatement -> {
            preparedStatement.setString(1, answer.getWriter());
            preparedStatement.setString(2, answer.getContents());
            preparedStatement.setString(3, String.valueOf(answer.getCreatedDate()));
            preparedStatement.setString(4, String.valueOf(answer.getQuestionId()));
        };

        jdbcTemplate.update(sql, preparedStatementSetter, keyHolder);
        return findByAnswerId(keyHolder.getId());

    }

    public Answer findByAnswerId(Long answerId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "SELECT * FROM Answers WHERE answerId = ?";

        PreparedStatementSetter preparedStatementSetter = preparedStatement ->
                preparedStatement.setString(1, String.valueOf(answerId));

        RowMapper<Answer> rowMapper = resultSet -> new Answer(
                resultSet.getLong("answerId"),
                resultSet.getString("writer"),
                resultSet.getString("contents"),
                resultSet.getTimestamp("createdDate"),
                resultSet.getInt("questionId")
        );
        return jdbcTemplate.queryForObject(sql, preparedStatementSetter, rowMapper);
    }

    public List<Answer> findAllByQuestionId(Long questionId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "SELECT * FROM Answers WHERE questionId = ?";

        PreparedStatementSetter preparedStatementSetter = preparedStatement ->
                preparedStatement.setString(1, String.valueOf(questionId));

        RowMapper<Answer> rowMapper = resultSet -> new Answer(
                resultSet.getLong("answerId"),
                resultSet.getString("writer"),
                resultSet.getString("contents"),
                resultSet.getTimestamp("createdDate"),
                resultSet.getInt("questionId")
        );
        return jdbcTemplate.query(sql, preparedStatementSetter, rowMapper);
    }
}
