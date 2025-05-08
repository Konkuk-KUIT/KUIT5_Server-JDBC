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
import java.util.List;

public class AnswerDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public List<Answer> findAllByQuestionId(int questionId) {
        String sql = "SELECT * FROM ANSWERS WHERE questionId=? ORDER BY answerId";

        PreparedStatementSetter pstmtSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) {
                try {
                    pstmt.setInt(1, questionId);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        RowMapper<Answer> rowMapper = new RowMapper<Answer>() {
            @Override
            public Answer mapRow(ResultSet rs)  {
                try {
                    return new Answer(
                        rs.getInt("answerId"),
                        rs.getInt("questionId"),
                        rs.getString("writer"),
                        rs.getString("contents"),
                        rs.getDate("createdDate")
                    );
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        return jdbcTemplate.query(sql, pstmtSetter, rowMapper);
    }

    public Answer insert(Answer answer) {
        KeyHolder holder = new KeyHolder();
        String sql = "INSERT INTO ANSWERS (writer, contents, createdDate, questionId) VALUES (?, ?, ?, ?)";
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) {
                try {
                    preparedStatement.setString(1, answer.getWriter());
                    preparedStatement.setString(2, answer.getContents());
                    preparedStatement.setObject(3, answer.getCreatedDate());
                    preparedStatement.setObject(4, answer.getQuestionId());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        jdbcTemplate.update(sql, preparedStatementSetter,holder);
        return findByAnswerId(holder.getId());
    }

    public Answer findByAnswerId(int answerId) {

        String sql = "SELECT answerId, writer, contents, createdDate, questionId " + "FROM ANSWERS WHERE answerId=?";
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement){
                try {
                    preparedStatement.setInt(1, answerId);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        };
        RowMapper<Answer> rowMapper = new RowMapper<Answer>() {
            @Override
            public Answer mapRow(ResultSet resultSet)  {
                try {
                    return new Answer(
                            resultSet.getInt("answerId"),
                            resultSet.getInt("questionId"),
                            resultSet.getString("writer"),
                            resultSet.getString("contents"),
                            resultSet.getDate("createdDate")
                    );
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        };

        return jdbcTemplate.queryForObject(sql, preparedStatementSetter, rowMapper);
    }
}
