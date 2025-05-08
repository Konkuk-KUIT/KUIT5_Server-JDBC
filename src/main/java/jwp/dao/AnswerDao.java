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

    public List<Answer> findAll() throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT * FROM Answers";
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
            }
        };
        RowMapper<Answer> rowMapper = new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet) throws SQLException {
                return new Answer(resultSet.getLong("answerId"),resultSet.getString("writer"),resultSet.getString("contents"),resultSet.getTimestamp("createdDate"),resultSet.getLong("questionId"));
            }
        };
        return jdbcTemplate.query(sql,preparedStatementSetter,rowMapper);
    }

    public Answer insert(Answer answer) throws SQLException{
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "INSERT INTO Answers (writer,contents, createdDate, questionId) VALUES (?,?,?,?)";

        KeyHolder keyHolder = new KeyHolder();
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, answer.getWriter());
                preparedStatement.setString(2, answer.getContents());
                preparedStatement.setTimestamp(3, answer.getCreatedDate());
                preparedStatement.setLong(4, answer.getQuestionId());
            }
        };
        jdbcTemplate.update(sql,preparedStatementSetter,keyHolder);
        return findByAnswerId((long)keyHolder.getId());
    }

    public Answer findByAnswerId(Long answerId) throws SQLException{
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT * FROM Answers WHERE answerId = ?";
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setLong(1, answerId);
            }
        };
        RowMapper<Answer> rowMapper = new RowMapper<Answer>() {
            @Override
            public Answer mapRow(ResultSet resultSet) throws SQLException {
                return new Answer(resultSet.getLong("answerId"),resultSet.getString("writer"),resultSet.getString("contents"),resultSet.getTimestamp("createdDate"),resultSet.getLong("questionId"));
            }
        };
        List<Answer> results = jdbcTemplate.query(sql,preparedStatementSetter,rowMapper);
        if(!results.isEmpty()){
            return results.get(0);
        }
        return null;
    }
}
