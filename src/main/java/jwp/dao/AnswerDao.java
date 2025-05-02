package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Answer;

import java.util.List;

public class AnswerDao {
//    public List<Answer> findAll() {
//        JdbcTemplate jdbcTemplate = new JdbcTemplate();
//        String sql = "SELECT * FROM ANSWERS";
//        RowMapper<Answer> rowMapper = resultSet -> new Answer(resultSet.getLong("answerId"),
//                resultSet.getString("writer"),
//                resultSet.getString("contents"),
//                resultSet.getTimestamp("createdDate"),
//                resultSet.getLong("questionId")
//        );
//
//        return jdbcTemplate.query(sql, rowMapper);
//    }

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
//
//    public void insert(Answer answer) {
//        JdbcTemplate jdbcTemplate = new JdbcTemplate();
//        String sql = "INSERT INTO ANSWERS (writer, contents, createdDate, questionId) VALUES (?, ?, ?, ?)";
//        PreparedStatementSetter preparedStatementSetter = preparedStatement -> {
//            preparedStatement.setString(1, answer.getWriter());
//            preparedStatement.setString(2, answer.getContent());
//            preparedStatement.setTimestamp(3, answer.getCreatedDate());
//            preparedStatement.setLong(4, answer.getQuestionId());
//        };
//        jdbcTemplate.update(sql, preparedStatementSetter);
//    }
}
