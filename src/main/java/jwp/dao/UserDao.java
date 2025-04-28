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

    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    private static UserDao userDao;

    private UserDao() {
    }

    public static UserDao getInstance() {
        if (userDao == null) {
            userDao = new UserDao();
            return userDao;
        }
        return userDao;
    }

    public void insert(User user){
        String sql = "INSERT INTO Users Values (?, ?, ?, ?)";
        jdbcTemplate.update(sql, preparedStatement -> {
            preparedStatement.setString(1, user.getUserId());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getEmail());
        });
    }

    public User findByUserId(String userId){
        String sql = "SELECT * FROM Users WHERE userId = ?";
        return jdbcTemplate.queryForObject(sql,
                preparedStatement -> {preparedStatement.setString(1, userId);},
                resultSet -> { return new User(
                        resultSet.getString("userId"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getString("email"));
                });
    }


    public void update(User user){
        String sql = "UPDATE Users SET password = ?, name = ?, email = ?";
        jdbcTemplate.update(sql, preparedStatement -> {
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getEmail());
        });
    }

    public List<User> findAll(){
        String sql = "SELECT * FROM Users";
        return jdbcTemplate.query(sql, preparedStatement -> {},
                resultSet -> { return new User(
                        resultSet.getString("userId"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getString("email"));
        });
    }
}