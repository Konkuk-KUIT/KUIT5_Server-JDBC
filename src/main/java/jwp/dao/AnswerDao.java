package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Answer;
import jwp.util.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AnswerDao {

    public List<Answer> findByQuestionId(int questionId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "SELECT * FROM Answers WHERE questionId = ?";

        PreparedStatementSetter preparedStatementSetter = preparedStatement -> {
            preparedStatement.setInt(1, questionId);
        };

        RowMapper rowMapper = resultSet -> new Answer(
                resultSet.getInt("answerId"),
                resultSet.getString("writer"),
                resultSet.getString("contents"),
                resultSet.getDate("createdDate"),
                resultSet.getInt("questionId")
        );
        return jdbcTemplate.query(sql, preparedStatementSetter, rowMapper);
    }

    public Answer insert(Answer answer) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "INSERT INTO Answers(writer, contents, createdDate, questionId) VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new KeyHolder();


        PreparedStatementSetter preparedStatementSetter = preparedStatement -> {

            preparedStatement.setString(1, answer.getWriter());
            preparedStatement.setString(2, answer.getContent());
            preparedStatement.setDate(3, answer.getCreatedDate());
            preparedStatement.setInt(4, answer.getQuestionId());
        };


        jdbcTemplate.update(sql, preparedStatementSetter, keyHolder);


        return findByAnswerId(keyHolder.getId());
    }

    public Answer findByAnswerId(int id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "SELECT * FROM Answers WHERE answerId = ?";

        PreparedStatementSetter preparedStatementSetter = preparedStatement -> {
            preparedStatement.setInt(1, id);
        };

        RowMapper rowMapper = new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet) throws SQLException {

                return new Answer(
                        resultSet.getInt("answerId"),
                        resultSet.getString("writer"),
                        resultSet.getString("contents"),
                        resultSet.getDate("createdDate"),
                        resultSet.getInt("questionId")
                );
            }
        };

        return (Answer) jdbcTemplate.queryForObject(sql, preparedStatementSetter, rowMapper);
    }
}
