package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Question;
import jwp.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class QuestionDao {

    public long getNewQuestionId() throws SQLException {
        return findAll().size()+1;
    }

    public List<Question> findAll() throws SQLException {

        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "SELECT * FROM Questions";
        PreparedStatementSetter preparedStatementSetter = preparedStatement -> {

        };
        RowMapper rowMapper = new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet) throws SQLException {
                return new Question(
                        resultSet.getLong("questionId"),
                        resultSet.getString("writer"),
                        resultSet.getString("title"),
                        resultSet.getString("contents"),
                        resultSet.getTimestamp("createdDate"),
                        resultSet.getInt("countOfAnswer")
                        );
            }
        };
        return (List<Question>) jdbcTemplate.query(sql, preparedStatementSetter, rowMapper);
    }

    public void insert(Question question) throws SQLException {

        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "INSERT INTO Questions VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, String.valueOf(question.getQuestionId()));
                preparedStatement.setString(2, question.getWriter());
                preparedStatement.setString(3, question.getTitle());
                preparedStatement.setString(4, question.getContents());
                preparedStatement.setString(5, String.valueOf(question.getCreatedDate()));
                preparedStatement.setString(6, String.valueOf(question.getCountOfAnswer()));
            }
        };

        jdbcTemplate.update(sql, preparedStatementSetter);
    }

    public Question findByQuestionId(long questionId) throws SQLException {

        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "SELECT * FROM Questions WHERE questionId = ?";
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, String.valueOf(questionId));
            }
        };
        RowMapper rowMapper = new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet) throws SQLException {
                return new Question(
                        resultSet.getLong("questionId"),
                        resultSet.getString("writer"),
                        resultSet.getString("title"),
                        resultSet.getString("contents"),
                        resultSet.getTimestamp("createdDate"),
                        resultSet.getInt("countOfAnswer")
                );
            }
        };

        return (Question) jdbcTemplate.queryForObject(sql, preparedStatementSetter, rowMapper);
    }

    public void updateCountOfAnswer(Question question) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "UPDATE Questions SET countOfAnswer = ? WHERE questionId = ?";
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, String.valueOf(question.getCountOfAnswer()));
                preparedStatement.setString(2, String.valueOf(question.getQuestionId()));
            }
        };

        jdbcTemplate.update(sql, preparedStatementSetter);
    }
}
