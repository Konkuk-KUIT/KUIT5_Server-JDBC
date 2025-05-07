package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Question;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class QuestionDao {
    public  List<Question> findAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT * FROM Questions";
        RowMapper<Question> rowMapper = new RowMapper<Question>() {
            @Override
            public Question mapRow(ResultSet resultSet) {
                try {
                    return new Question(resultSet.getInt("questionId"),
                            resultSet.getString("writer"),
                            resultSet.getString("title"),
                            resultSet.getString("contents"),
                            resultSet.getTimestamp("createdDate"),
                            resultSet.getInt("countOfAnswer")
                    );
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        };
        return jdbcTemplate.query(sql, preparedStatement -> {}, rowMapper);
    }

    public Question insert(Question question, KeyHolder holder) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "INSERT INTO Questions (writer, title, contents, createdDate, countOfAnswer) VALUES (?, ?, ?, ?, ?)";
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) {
                try {
                    preparedStatement.setString(1, question.getWriter());
                    preparedStatement.setString(2, question.getTitle());
                    preparedStatement.setString(3, question.getContent());
                    preparedStatement.setTimestamp(4, question.getCreatedDate());
                    preparedStatement.setInt(5, question.getCountOfAnswer());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        jdbcTemplate.update(sql, preparedStatementSetter,holder);
        return findByQuestionId(holder.getId());
    }

    public Question findByQuestionId(int questionId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "SELECT * FROM Questions WHERE questionId = ?";
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement){
                try {
                    preparedStatement.setLong(1, questionId);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        };
        RowMapper<Question> rowMapper = new RowMapper<Question>() {
            @Override
            public Question mapRow(ResultSet resultSet)  {
                try {
                    return  new Question(
                            resultSet.getInt("questionId"),
                            resultSet.getString("writer"),
                            resultSet.getString("title"),
                            resultSet.getString("contents"),
                            resultSet.getTimestamp("createdDate"),
                            resultSet.getInt("countOfAnswer")
                    );
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        };

        return jdbcTemplate.queryForObject(sql, preparedStatementSetter, rowMapper);
    }

    public void updateCountOfAnswer(Question question) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "UPDATE QUESTIONS SET countOfAnswer=? WHERE questionId=?";
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement){
                try {
                    preparedStatement.setInt(1, question.getCountOfAnswer());
                    preparedStatement.setInt(2, question.getQuestionId());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        };
        jdbcTemplate.update(sql, preparedStatementSetter);
    }
}
