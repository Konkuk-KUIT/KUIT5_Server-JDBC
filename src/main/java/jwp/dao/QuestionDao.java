package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.KeyHolder;
import jwp.model.Question;
import jwp.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class QuestionDao {
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();
    private KeyHolder keyHolder = new KeyHolder();

    public KeyHolder getKeyHolder() {
        return keyHolder;
    }

    public Question insert(Question question) throws SQLException {
        String sql = "INSERT INTO QUESTIONS(writer, title, contents, createdDate, countOfAnswer) VALUES(?, ?, ?, ?, ?)";
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {


                preparedStatement.setString(1, question.getWriter());
                preparedStatement.setString(2, question.getTitle());
                preparedStatement.setString(3, question.getContents());
                preparedStatement.setString(4, Timestamp.valueOf(question.getCreatedDate()).toString());
                preparedStatement.setString(5, Integer.toString(question.getCountOfAnswer()));
            }
        };

        jdbcTemplate.update(sql, preparedStatementSetter, keyHolder);

        return findByQuestionId(keyHolder.getKey().toString());
    }

    public Question findByQuestionId(String questionId) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT * FROM QUESTIONS WHERE questionId = ?";
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, questionId);
            }
        };
        RowMapper rowMapper = new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet) throws SQLException {
                return new Question(
                        Long.parseLong(resultSet.getString("questionId")),
                        resultSet.getString("writer"),
                        resultSet.getString("title"),
                        resultSet.getString("contents"),
                        resultSet.getTimestamp("createdDate").toLocalDateTime(),
                        Integer.parseInt(resultSet.getString("countOfAnswer"))
                );
            }
        };
        return (Question) jdbcTemplate.queryForObject(sql, preparedStatementSetter, rowMapper);
    }
//
//    public void update(Question question) throws SQLException {
//        JdbcTemplate jdbcTemplete = new JdbcTemplate();
//        String sql = "UPDATE USERS SET questionId = ?, writer = ?, title = ?, contents = ?, createdDate = ?, countOfAnswer = ? WHERE questionId = ?";
//        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement preparedStatement) throws SQLException {
//                preparedStatement.setString(1, Long.toString(question.getQuestionId()));
//                preparedStatement.setString(2, question.getWriter());
//                preparedStatement.setString(3, question.getTitle());
//                preparedStatement.setString(4, question.getContents());
//                preparedStatement.setString(5, question.getCreatedDate().toString());
//                preparedStatement.setString(6, Integer.toString(question.getCountOfAnswer()));
//                preparedStatement.setString(7, Long.toString(question.getQuestionId()));
//            }
//        };
//        jdbcTemplete.update(sql, preparedStatementSetter);
//    }

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
                return new Question(
                        Long.parseLong(resultSet.getString("questionId")),
                        resultSet.getString("writer"),
                        resultSet.getString("title"),
                        resultSet.getString("contents"),
                        resultSet.getTimestamp("createdDate").toLocalDateTime(),
                        Integer.parseInt(resultSet.getString("countOfAnswer"))
                );
            }
        };
        return(List<Question>)jdbcTemplate.query(sql, preparedStatementSetter, rowMapper);
    }
}
