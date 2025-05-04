package jwp.dao;

import core.jdbc.JdbcTemplate;
import jwp.model.Question;


import java.util.List;

public class QuestionDao {
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public void insert(Question question) {
        String sql = "INSERT INTO Questions (writer, title, contents, createdDate, countOfAnswer) VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, preparedStatement -> {
            preparedStatement.setString(1, question.getWriter());
            preparedStatement.setString(2, question.getTitle());
            preparedStatement.setString(3, question.getContents());
            preparedStatement.setString(4, String.valueOf(question.getCreatedDate()));
            preparedStatement.setString(5, String.valueOf(question.getCountOfAnswer()));});
    }

    public Question findByQuestionId(Long questionId) {
        String sql = "SELECT * FROM Questions WHERE questionId = ?";

        return jdbcTemplate.queryForObject(sql, preparedStatement ->
                preparedStatement.setString(1, String.valueOf(questionId)),
                resultSet -> new Question(
                        resultSet.getLong("questionId"),
                        resultSet.getString("writer"),
                        resultSet.getString("title"),
                        resultSet.getString("contents"),
                        resultSet.getTimestamp("createdDate").toLocalDateTime(),
                        resultSet.getInt("countOfAnswer")));
    }

    public List<Question> findAll() {
        String sql = "SELECT * FROM Questions";

        return jdbcTemplate.query(sql, preparedStatementSetter->{},
                resultSet -> new Question(

                        resultSet.getLong("questionId"),
                        resultSet.getString("writer"),
                        resultSet.getString("title"),
                        resultSet.getString("contents"),
                        resultSet.getTimestamp("createdDate").toLocalDateTime(),
                        resultSet.getInt("countOfAnswer")
                ));
    }

}