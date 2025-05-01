package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Question;
import jwp.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class QuestionDao {
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
        return (List<Question>)jdbcTemplate.query(sql, preparedStatementSetter, rowMapper);

    }
}
