package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Question;
import jwp.model.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class QuestionDao {

    public void insert(Question question) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "INSERT INTO Question VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                try {
                    preparedStatement.setInt(1, question.getQuestionId());
                    preparedStatement.setString(2, question.getWriter());
                    preparedStatement.setString(3, question.getTitle());
                    preparedStatement.setString(4, question.getContent());
                    preparedStatement.setDate(5, question.getCreatedDate());
                    preparedStatement.setInt(6, question.getCountOfAnswer());
                }catch (SQLException e)
                {
                    throw new RuntimeException(e);
                }

            }
        };
    }

    public List<Question> findAll() {


        JdbcTemplate jdbcTemplate = new JdbcTemplate();


        String sql = "SELECT * FROM Questions";

        PreparedStatementSetter preparedStatementSetter = preparedStatement -> {
        };

        RowMapper rowMapper = resultSet -> {
            try {
                return new Question(resultSet.getInt("questionId"),
                        resultSet.getString("writer"),
                        resultSet.getString("title"),
                        resultSet.getString("contents"),
                        resultSet.getDate("createdDate"),
                        resultSet.getInt("countOfAnswer")
                );

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };

        return jdbcTemplate.query(sql, preparedStatementSetter, rowMapper);

    }
}
