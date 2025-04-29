package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Answer;
import jwp.model.KeyHolder;
import jwp.model.Question;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class AnswerDao {
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();
    private KeyHolder keyHolder = new KeyHolder();
    public KeyHolder getKeyHolder() {
        return keyHolder;
    }

    public Answer insert(Answer answer) throws SQLException {
        String sql = "INSERT INTO ANSWERS(writer, contents, createdDate, questionId) VALUES(?, ?, ?, ?)";
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, answer.getWriter());
                preparedStatement.setString(2, answer.getContents());
                preparedStatement.setTimestamp(3, answer.getCreatedDate());
                preparedStatement.setLong(4, answer.getQuestionId());
            }
        };

        jdbcTemplate.update(sql, preparedStatementSetter, keyHolder);

        return findByAnswerId(keyHolder.getKey().toString());
    }

    public Answer findByAnswerId(String answerId) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT * FROM ANSWERS WHERE answerId = ?";
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, answerId);
            }
        };
        RowMapper rowMapper = new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet) throws SQLException {
                return new Answer(
                        resultSet.getLong("answerId"),
                        resultSet.getString("writer"),
                        resultSet.getString("contents"),
                        resultSet.getTimestamp("createdDate").toLocalDateTime(),
                        resultSet.getLong("questionId")
                );
            }
        };
        return (Answer) jdbcTemplate.queryForObject(sql, preparedStatementSetter, rowMapper);
    }

    public void update(Answer answer) throws SQLException {
        String sql = "UPDATE ANSWERS SET questionId = ?, writer = ?, contents = ?, questionId = ? WHERE questionId = ?";
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(2, answer.getWriter());
                preparedStatement.setString(3, answer.getContents());
                preparedStatement.setLong(4, answer.getQuestionId());
            }
        };
        jdbcTemplate.update(sql, preparedStatementSetter, null);
    }

    public List<Answer> findAllWithQuestionId(String questionId) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT * FROM ANSWERS WHERE questionId = ?";
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, questionId);
            }
        };
        RowMapper rowMapper = new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet) throws SQLException {
                return new Answer(
                        resultSet.getLong("answerId"),
                        resultSet.getString("writer"),
                        resultSet.getString("contents"),
                        resultSet.getTimestamp("createdDate").toLocalDateTime(),
                        resultSet.getLong("questionId")
                );
            }
        };
        return(List<Answer>)jdbcTemplate.query(sql, preparedStatementSetter, rowMapper);
    }

    public List<Answer> findAll() throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT * FROM ANSWERS";
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
                        resultSet.getTimestamp("createdDate").toLocalDateTime(),
                        resultSet.getLong("questionId")
                );
            }
        };
        return(List<Answer>)jdbcTemplate.query(sql, preparedStatementSetter, rowMapper);
    }
}
