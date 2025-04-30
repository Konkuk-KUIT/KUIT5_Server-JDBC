package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Question;

import java.util.List;

public class QuestionDao {
    public Question insert(Question question) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        KeyHolder keyHolder = new KeyHolder();

        String sql = "INSERT INTO Questions (writer, title, contents, createdDate, countOfAnswer) VALUES (?, ?, ?, ?, ?)";

        PreparedStatementSetter preparedStatementSetter = preparedStatement -> {
            preparedStatement.setString(1, question.getWriter());
            preparedStatement.setString(2, question.getTitle());
            preparedStatement.setString(3, question.getContents());
            preparedStatement.setString(4, String.valueOf(question.getCreatedDate()));
            preparedStatement.setString(5, String.valueOf(question.getCountOfAnswer()));

        };

        jdbcTemplate.update(sql, preparedStatementSetter, keyHolder);
        return findByQuestionId(keyHolder.getId());

    }

    public Question findByQuestionId(Long questionId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "SELECT * FROM Questions WHERE questionId = ?";

        PreparedStatementSetter preparedStatementSetter = preparedStatement ->
                preparedStatement.setString(1, String.valueOf(questionId));

        RowMapper<Question> rowMapper = resultSet -> new Question(

                resultSet.getLong("questionId"),
                resultSet.getString("writer"),
                resultSet.getString("title"),
                resultSet.getString("contents"),
                resultSet.getTimestamp("createdDate").toLocalDateTime(),
                resultSet.getInt("countOfAnswer")
        );
        return jdbcTemplate.queryForObject(sql, preparedStatementSetter, rowMapper);
    }

    public List<Question> findAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "SELECT * FROM Questions";

        PreparedStatementSetter preparedStatementSetter = preparedStatement -> {};

        RowMapper<Question> rowMapper = resultSet -> new Question(

                resultSet.getLong("questionId"),
                resultSet.getString("writer"),
                resultSet.getString("title"),
                resultSet.getString("contents"),
                resultSet.getTimestamp("createdDate").toLocalDateTime(),
                resultSet.getInt("countOfAnswer")
        );

        return jdbcTemplate.query(sql, preparedStatementSetter, rowMapper);
    }

    public Question update(Question question) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        KeyHolder keyHolder = new KeyHolder();

        String sql = "UPDATE Questions SET title = ?, contents = ?, countOfAnswer = ? WHERE questionId = ?";

        PreparedStatementSetter preparedStatementSetter = preparedStatement -> {
            preparedStatement.setString(1, question.getTitle());
            preparedStatement.setString(2, question.getContents());
            preparedStatement.setString(3, String.valueOf(question.getCountOfAnswer()));
            preparedStatement.setString(4, String.valueOf(question.getQuestionId()));
        };

        jdbcTemplate.update(sql, preparedStatementSetter, keyHolder);
        return findByQuestionId(keyHolder.getId());
    }

}
