package jwp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import core.jdbc.JdbcTemplate;
import jwp.model.User;

public class UserDao {
    public void insert(User user) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {

            @Override
            public String createQuery() {
                return "INSERT INTO Users VALUES (?, ?, ?, ?)";
            }

            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, user.getUserId());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getName());
                preparedStatement.setString(4, user.getEmail());
            }

            @Override
            public Object mapRow(ResultSet resultSet) throws SQLException {
                return null;
            }
        };

        jdbcTemplate.update();
    }

    public User findByUserId(String userId) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            public String createQuery() {
                return "SELECT * FROM Users WHERE UserId = ?";
            }

            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, userId);
            }

            @Override
            public Object mapRow(ResultSet resultSet) throws SQLException {
                return new User(resultSet.getString("userId"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getString("email")
                );
            }
        };
        return (User)jdbcTemplate.queryForObject();
    }

    public void update(User user) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {

            @Override
            public String createQuery() {
                return "UPDATE Users SET password = ?, name = ?, email = ? ";
            }

            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, user.getPassword());
                preparedStatement.setString(2, user.getName());
                preparedStatement.setString(3, user.getEmail());
            }

            @Override
            public Object mapRow(ResultSet resultSet) throws SQLException {
                return null;
            }
        };
        jdbcTemplate.update();
    }

    public List<User> findAll() throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            public String createQuery() {
                return "SELECT * FROM Users";
            }

            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {

            }

            @Override
            public Object mapRow(ResultSet resultSet) throws SQLException {
                return new User(resultSet.getString("userId"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getString("email")
                );
            }
        };
        return (List<User>)jdbcTemplate.query();
    }
}