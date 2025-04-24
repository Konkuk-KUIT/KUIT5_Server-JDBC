package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import jwp.model.Question;

public class QuestionDao {

    public List<Question> findAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT * FROM Questions";
        RowMapper<Question> rowMapper = new RowMapper<Question>() {
            @Override
            public Question mapRow(ResultSet resultSet) throws SQLException {
                return new Question(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getTimestamp(5), resultSet.getInt(6));
            }
        };
        return jdbcTemplate.query(sql, preparedStatement -> {
        }, rowMapper);
    }

    public Question insert(Question question) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate) VALUES (?, ?, ?, ?)";

        PreparedStatementSetter preparedStatementSetter = pstmt -> {
            pstmt.setString(1, question.getWriter());
            pstmt.setString(2, question.getTitle());
            pstmt.setString(3, question.getContent());
            pstmt.setTimestamp(4, question.getCreatedDate());
        };
        KeyHolder keyHolder = new KeyHolder();
        jdbcTemplate.update(sql, preparedStatementSetter, keyHolder);
        return findByQuestionId(keyHolder.getId());
    }

    public Question findByQuestionId(int questionId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer " +
                "FROM QUESTIONS WHERE questionId=?";

        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setInt(1, questionId);
        };

        RowMapper<Question> rowMapper = rs -> new Question(rs.getInt("questionId"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate"),
                rs.getInt("countOfAnswer"));

        return jdbcTemplate.queryForObject(sql, pstmtSetter, rowMapper);
    }

    public void updateCountOfAnswer(Question question) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "UPDATE QUESTIONS SET countOfAnswer=? WHERE questionId=?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setInt(1, question.getCountOfAnswer());
            pstmt.setInt(2, question.getQuestionId());
        };
        jdbcTemplate.update(sql, pstmtSetter);
    }
}
