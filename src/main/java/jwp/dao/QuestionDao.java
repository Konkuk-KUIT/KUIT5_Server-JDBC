package jwp.dao;

import core.jdbc.JdbcTemplate;
import jwp.model.Question;

import java.util.List;

public class QuestionDao {

    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    private static QuestionDao questionDao;

    private QuestionDao() {
    }

    public static QuestionDao getInstance() {
        if (questionDao == null) {
            questionDao = new QuestionDao();
            return questionDao;
        }
        return questionDao;
    }

    public List<Question> findAll() {
        String sql = "SELECT * FROM Questions";
        return jdbcTemplate.query(sql, preparedStatement -> {},
                resultSet -> { return new Question(
                        resultSet.getLong("questionId"),
                        resultSet.getString("writer"),
                        resultSet.getString("title"),
                        resultSet.getString("contents"),
                        resultSet.getTimestamp("createdDate").toLocalDateTime(),
                        resultSet.getInt("countofAnswer")); });
    }

    public void insert(Question question) {
        String sql = "INSERT INTO Questions Values (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, preparedStatement -> {
            preparedStatement.setString(1, question.getWriter());
            preparedStatement.setString(2, question.getTitle());
            preparedStatement.setString(3, question.getContents());
            preparedStatement.setTimestamp(4, question.getCreatedDate());
            preparedStatement.setInt(5, question.getCountOfAnswer());
        });
    }
}
