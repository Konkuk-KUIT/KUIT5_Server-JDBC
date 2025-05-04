package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Answer;

import java.util.List;

public class AnswerDao {

    public List<Answer> findByQuestionId(int questionId){
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
}
