package jwp.dao;

import core.jdbc.JdbcTemplate;
import jwp.model.Answer;
import jwp.model.KeyHolder;

import java.util.List;

public class AnswerDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    private static AnswerDao answerDao;

    public AnswerDao() {
    }

    public static AnswerDao getInstance() {
        if (answerDao == null) {
            answerDao = new AnswerDao();
            return answerDao;
        }
        return answerDao;
    }

    public void insert(Answer answer) {
        String sql = "INSERT INTO Answers (writer, contents, createdDate, questionId) Values (?, ?, ?, ?)";
        KeyHolder keyHolder = new KeyHolder();
        jdbcTemplate.update(sql, preparedStatement -> {
            preparedStatement.setString(1, answer.getWriter());
            preparedStatement.setString(2, answer.getContents());
            preparedStatement.setTimestamp(3, answer.getCreatedDate());
            preparedStatement.setLong(4, answer.getQuestionId());
        }, keyHolder);
        answer.setAnswerId(keyHolder.getId());
    }

    public List<Answer> findByQuestionId(long questionId) {
        String sql = "SELECT * FROM Answers WHERE questionId = ?";
        return jdbcTemplate.query(sql, preparedStatement -> {
            preparedStatement.setLong(1, questionId);
        }, resultSet -> {
            return new Answer(resultSet.getLong("answerId"),
                    resultSet.getString("writer"),
                    resultSet.getString("contents"),
                    resultSet.getTimestamp("createdDate").toLocalDateTime(),
                    resultSet.getLong("questionId")
            );
        });
    }
}
