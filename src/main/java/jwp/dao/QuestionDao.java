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
    public Question insert(Question question) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "INSERT INTO Questions (writer, title, contents, createdDate, countOfAnswer) VALUES(?,?,?,?,?)";
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, question.getWriter());
                preparedStatement.setString(2, question.getTitle());
                preparedStatement.setString(3, question.getContents());
                preparedStatement.setTimestamp(4, question.getCreatedDate());
                preparedStatement.setInt(5,question.getCountOfAnswer());
            }
        };
        KeyHolder keyHolder = new KeyHolder();
        jdbcTemplate.update(sql, preparedStatementSetter, keyHolder);
        Question insertedQuestion = findByQuestionId(String.valueOf(keyHolder.getId()));

        return insertedQuestion;
    }

    public Question findByQuestionId(String questionId) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "SELECT * FROM Questions WHERE QuestionId = ?";
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, questionId);
            }
        };
        RowMapper rowMapper = new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet) throws SQLException {
                return new Question(resultSet.getLong("questionId"),resultSet.getString("writer"),
                        resultSet.getString("title"),resultSet.getString("contents"),
                        resultSet.getTimestamp("createdDate"),resultSet.getInt("countOfAnswer"));
            }
        };
        return jdbcTemplate.queryForObject(sql, preparedStatementSetter, rowMapper);
    }

    public List<Question> findAll() throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "SELECT * FROM Questions";
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {

            }
        };
        RowMapper rowMapper = new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet) throws SQLException {
                return new Question(resultSet.getLong("questionId"),resultSet.getString("writer"),
                        resultSet.getString("title"),resultSet.getString("contents"),
                        resultSet.getTimestamp("createdDate"),resultSet.getInt("countOfAnswer"));
            }
        };
        return jdbcTemplate.query(sql, preparedStatementSetter, rowMapper);
    }

    public void updateCountOfAnswer(Question question) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "UPDATE QUESTIONS SET countOfAnswer=? WHERE questionId=?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setInt(1, question.getCountOfAnswer());
            pstmt.setLong(2, question.getQuestionId());
        };
        jdbcTemplate.update(sql, pstmtSetter);
    }
}
