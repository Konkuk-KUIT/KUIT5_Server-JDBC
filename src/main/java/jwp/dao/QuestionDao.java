package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Question;
import jwp.model.User;
import jwp.util.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class QuestionDao{

    public Question insert(Question question) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        KeyHolder keyHolder = new KeyHolder();
        String sql = "INSERT INTO Questions(writer, title, contents, createdDate, countOfAnswer) VALUES (?, ?, ?, ?, ?)";

        PreparedStatementSetter preparedStatementSetter = preparedStatement -> {
            try {
                preparedStatement.setString(1, question.getWriter());
                preparedStatement.setString(2, question.getTitle());
                preparedStatement.setString(3, question.getContent());
                preparedStatement.setDate(4, question.getCreatedDate());
                preparedStatement.setInt(5, question.getCountOfAnswer());
            }catch (SQLException e)
            {
                throw new RuntimeException(e);
            }

        };

        jdbcTemplate.update(sql, preparedStatementSetter, keyHolder);



        return findByQuestionId(keyHolder.getId());
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

    public Question findByQuestionId(Integer questionId){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "SELECT * FROM Questions WHERE questionId = ?";

        PreparedStatementSetter preparedStatementSetter = preparedStatement ->{
            preparedStatement.setInt(1,questionId);
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

        return (Question) jdbcTemplate.queryForObject(sql, preparedStatementSetter, rowMapper);
    }
}
