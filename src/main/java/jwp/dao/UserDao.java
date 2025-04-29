package jwp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.User;

public class UserDao {
    public void insert(User user) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "INSERT INTO Users VALUES(?,?,?,?)";
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, user.getUserId());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getName());
                preparedStatement.setString(4, user.getEmail());
            }
        };
        jdbcTemplate.update(sql, preparedStatementSetter);
    }

    public User findByUserId(String userId) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "SELECT * FROM Users WHERE UserId = ?";
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, userId);
            }
        };
        RowMapper rowMapper = new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet) throws SQLException {
                return new User(resultSet.getString("userId"), resultSet.getString("password"), resultSet.getString("name"), resultSet.getString("email"));
            }
        };
        return (User) jdbcTemplate.queryForObject(sql, preparedStatementSetter, rowMapper);
    }

    public void update(User user) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "UPDATE Users SET password = ?, name = ?, email = ? WHERE UserId = ?";
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, user.getPassword());
                preparedStatement.setString(2, user.getName());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getUserId());

            }
        };
        jdbcTemplate.update(sql, preparedStatementSetter);
    }

    public List<User> findAll() throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "SELECT * FROM Users";
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {

            }
        };
        RowMapper rowMapper = new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet) throws SQLException {
                return new User(resultSet.getString("UserId"), resultSet.getString("password"), resultSet.getString("name"), resultSet.getString("email"));
            }
        };
        return (List<User>)jdbcTemplate.query(sql, preparedStatementSetter, rowMapper);
    }
}