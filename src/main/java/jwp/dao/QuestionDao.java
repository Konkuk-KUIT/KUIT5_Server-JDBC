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
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public List<Question> findAll() throws SQLException {
        String sql = "SELECT * FROM QUESTIONS ORDER BY questionId DESC";

        RowMapper<Question> rowMapper = new RowMapper<Question>() {
            @Override
            public Question mapRow(ResultSet resultSet) throws SQLException {
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

        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
            }
        };

        return jdbcTemplate.query(sql, preparedStatementSetter, rowMapper);
    }

    public Question insert(Question question) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new KeyHolder();

        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, question.getWriter());
                preparedStatement.setString(2, question.getTitle());
                preparedStatement.setString(3, question.getContents());
                preparedStatement.setTimestamp(4, question.getCreatedDate());
                preparedStatement.setInt(5, question.getCountOfAnswer());
            }
        };

        jdbcTemplate.update(sql, preparedStatementSetter, keyHolder);

        return findByQuestionId((long) keyHolder.getId());}

    public Question findByQuestionId(Long questionId) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT * FROM QUESTIONS WHERE questionId = ?";

        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setLong(1, questionId);
            }
        };

        RowMapper<Question> rowMapper = new RowMapper<Question>() {
            @Override
            public Question mapRow(ResultSet resultSet) throws SQLException {
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

        return jdbcTemplate.queryForObject(sql, preparedStatementSetter, rowMapper);
    }
}
