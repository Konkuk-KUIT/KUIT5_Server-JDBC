package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Answer;
import jwp.model.Question;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class AnswerDao {
    public List<Answer> findByQuestionId(long questionId) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT * FROM Answers WHERE questionId = ?";
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setLong(1, questionId);
            }
        };
        RowMapper<Answer> rowMapper = new RowMapper<Answer>() {
            @Override
            public Answer mapRow(ResultSet resultSet) throws SQLException {
                return new Answer(
                        resultSet.getLong("answerId"),
                        resultSet.getString("writer"),
                        resultSet.getString("contents"),
                        resultSet.getTimestamp("createdDate"),
                        resultSet.getLong("questionId")
                );
            }
        };
        return jdbcTemplate.query(sql, preparedStatementSetter,rowMapper);
    }

    public Answer findByAnswerId(long answerId) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT * FROM Answers WHERE answerId = ?";

        return jdbcTemplate.queryForObject(sql,
                ps -> ps.setLong(1, answerId),
                rs -> new Answer(
                        rs.getLong("answerId"),
                        rs.getString("writer"),
                        rs.getString("contents"),
                        rs.getTimestamp("createdDate"),
                        rs.getLong("questionId")
                )
        );
    }


    public Answer insert(Answer answer) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "INSERT INTO ANSWERS (writer, contents, createdDate, questionId) VALUES(?, ?, ?, ?)";
        KeyHolder keyHolder = new KeyHolder();

        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, answer.getWriter());
                preparedStatement.setString(2, answer.getContents());
                preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                preparedStatement.setLong(4, answer.getQuestionId());
            }
        };
        jdbcTemplate.update(sql, preparedStatementSetter, keyHolder);

        return findByAnswerId(keyHolder.getId());
    }
}
