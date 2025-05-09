package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Answer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AnswerDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public List<Answer> findAllByQuestionId(String questionId) throws SQLException {
        String sql = "SELECT * FROM Answers where questionId = ?";
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, questionId);
            }
        };
        RowMapper rowMapper = new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet) throws SQLException {
                return new Answer(resultSet.getLong("answerId"),resultSet.getString("writer"),
                        resultSet.getString("contents"),
                        resultSet.getTimestamp("createdDate"),resultSet.getLong("questionId"));
            }
        };
        return jdbcTemplate.query(sql, preparedStatementSetter, rowMapper);
    }

    public Answer insert(Answer answer) throws SQLException {
        KeyHolder keyHolder = new KeyHolder();
        String sql = "INSERT INTO ANSWERS (writer, contents, createdDate, questionId) VALUES (?, ?, ?, ?)";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, answer.getWriter());
            pstmt.setString(2, answer.getContents());
            pstmt.setObject(3, answer.getCreatedDate());
            pstmt.setObject(4, answer.getQuestionId());
        };
        jdbcTemplate.update(sql, pstmtSetter, keyHolder);
        return findByAnswerId(keyHolder.getId());
    }

    public Answer findByAnswerId(int answerId) throws SQLException {
        String sql = "SELECT answerId, writer, contents, createdDate, questionId " +
                "FROM ANSWERS WHERE answerId=?";

        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setInt(1, answerId);
        };

        RowMapper<Answer> rowMapper = rs -> new Answer(rs.getLong("answerId"),
                rs.getString("writer"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate"),
                rs.getLong("questionId"));

        return jdbcTemplate.queryForObject(sql, pstmtSetter, rowMapper);
    }
}
