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
    public void insert(Answer answer) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "INSERT INTO Answers (writer, contents, createdDate, questionId) VALUES (?, ?, ?, ?)";
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, answer.getWriter());
                ps.setString(2, answer.getContents());
                ps.setTimestamp(3, answer.getCreatedDate());
                ps.setLong(4, answer.getQuestionId());
            }
        };

        jdbcTemplate.update(sql, preparedStatementSetter);

        String updateSql = "UPDATE Questions SET countOfAnswer = countOfAnswer + 1 WHERE questionId = ?";
        PreparedStatementSetter updatePreparedStatementSetter = ps -> ps.setLong(1, answer.getQuestionId());

        jdbcTemplate.update(updateSql, updatePreparedStatementSetter);
    }

    public List<Answer> findAllByQuestionId(Long questionId) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "SELECT * FROM Answers WHERE questionId = ?";
        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setLong(1, questionId);
            }
        };
        RowMapper<Answer> rowMapper = new RowMapper<Answer>() {
            @Override
            public Answer mapRow(ResultSet rs) throws SQLException {
                return new Answer(
                        rs.getLong("answerId"),
                        rs.getString("writer"),
                        rs.getString("contents"),
                        rs.getTimestamp("createdDate"),
                        rs.getLong("questionId")
                );
            }
        };

        return jdbcTemplate.query(sql, pss, rowMapper);
    }
}
