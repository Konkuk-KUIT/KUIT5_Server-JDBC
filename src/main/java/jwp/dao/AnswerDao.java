package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Answer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AnswerDao {

    public List<Answer> findAnswersByQuestionId(long questionId) throws SQLException {

        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "SELECT * FROM Answers WHERE answerId = ?";
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, String.valueOf(questionId));
            }
        };
        RowMapper rowMapper = new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet) throws SQLException {
                return new Answer(
                        resultSet.getLong("answerId"),
                        resultSet.getString("writer"),
                        resultSet.getString("contents"),
                        resultSet.getTimestamp("createdDate"),
                        resultSet.getLong("questionId")
                );
            }
        };

        return (List<Answer>) jdbcTemplate.query(sql, preparedStatementSetter, rowMapper);
    }

    public long getNewAnswerId(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "SELECT * FROM Answers";
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
            }
        };
        RowMapper rowMapper = new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet) throws SQLException {
                return new Answer(
                        resultSet.getLong("answerId"),
                        resultSet.getString("writer"),
                        resultSet.getString("contents"),
                        resultSet.getTimestamp("createdDate"),
                        resultSet.getLong("questionId")
                );
            }
        };

        return jdbcTemplate.query(sql, preparedStatementSetter, rowMapper).size()+1;
    }

    public void insert(Answer answer) throws SQLException {

        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "INSERT INTO Answers VALUES (?, ?,  ?, ?, ?)";
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, String.valueOf(answer.getAnswerId()));
                preparedStatement.setString(2, answer.getWriter());
                preparedStatement.setString(3, answer.getContents());
                preparedStatement.setString(4, String.valueOf(answer.getCreatedDate()));
                preparedStatement.setString(5, String.valueOf(answer.getQuestionId()));
            }
        };

        jdbcTemplate.update(sql, preparedStatementSetter);
    }
}
