package jwp.dao;

import com.sun.jdi.LongValue;
import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Question;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class QuestionDao {
    public List<Question> findAll() throws SQLException{
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
                return new Question(resultSet.getLong("questionId"),resultSet.getString("writer"),resultSet.getString("title"),resultSet.getString("contents")
                ,resultSet.getTimestamp("createdDate"),resultSet.getInt("countOfAnswer"));
            }
        };
        return jdbcTemplate.query(sql,preparedStatementSetter,rowMapper);
    }
    public Question insert(Question question) throws SQLException{
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "INSERT INTO Questions (writer, title, contents, createdDate, countOfAnswer) VALUES (?,?,?,?,?)";

        KeyHolder keyHolder = new KeyHolder();
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1,question.getWriter());
                preparedStatement.setString(2,question.getTitle());
                preparedStatement.setString(3,question.getContents());
                preparedStatement.setTimestamp(4,question.getCreatedDate());
                preparedStatement.setInt(5,question.getCountOfAnswer());
            }
        };
        jdbcTemplate.update(sql,preparedStatementSetter,keyHolder);
        return findByQuestionId((long)keyHolder.getId());
    }
    public Question findByQuestionId(Long questionId) throws SQLException{
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT * FROM Questions WHERE questionId = ?";

        PreparedStatementSetter setter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setLong(1,questionId);
            }
        };
        RowMapper<Question> rowMapper = new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet) throws SQLException {
                return new Question(resultSet.getLong("questionId"),resultSet.getString("writer"),resultSet.getString("title"),resultSet.getString("contents"),resultSet.getTimestamp("createdDate"),resultSet.getInt("countOfAnswer"));
            }
        };

        List<Question> results = jdbcTemplate.query(sql,setter,rowMapper);
        if(!results.isEmpty()){
            return results.get(0);
        }
        return null;
    }
}
