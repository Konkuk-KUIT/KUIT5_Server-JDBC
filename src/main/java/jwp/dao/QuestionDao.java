package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.KeyHolder;
import jwp.model.Question;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class QuestionDao {
    public List<Question> findAll() throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT * FROM Questions";
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {

            }
        };
        RowMapper rowMapper = new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet) throws SQLException {
                return new Question(resultSet.getLong("questionId"),
                        resultSet.getString("writer"),
                        resultSet.getString("title"),
                        resultSet.getString("contents"),
                        Timestamp.valueOf(resultSet.getString("createdDate")),
                        Integer.parseInt(resultSet.getString("countOfAnswer")));
            }
        };
        return (List<Question>) jdbcTemplate.query(sql, preparedStatementSetter, rowMapper);
    }

    public Question insert(String writer, String title, String contents) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "INSERT INTO Questions (writer, title, contents, createdDate, countOfAnswer) VALUES(?,?,?,?,?)";
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, writer);
                preparedStatement.setString(2, title);
                preparedStatement.setString(3, contents);
                preparedStatement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
                preparedStatement.setInt(5, 0);
            }
        };
        KeyHolder keyHolder = new KeyHolder();
        jdbcTemplate.update(sql, preparedStatementSetter, keyHolder);

        return findByQuestionId(keyHolder.getId());
    }

    public Question findByQuestionId(long id) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "SELECT * FROM Questions WHERE questionId = ?";
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setLong(1, id);
            }
        };
        RowMapper rowMapper = new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet) throws SQLException {
                return new Question(resultSet.getLong("questionId"),
                        resultSet.getString("writer"),
                        resultSet.getString("title"),
                        resultSet.getString("contents"),
                        Timestamp.valueOf(resultSet.getString("createdDate")),
                        Integer.parseInt(resultSet.getString("countOfAnswer")));
            }
        };
        return (Question) jdbcTemplate.queryForObject(sql, preparedStatementSetter, rowMapper);
    }
}
