package jwp.dao;

import java.sql.SQLException;
import java.util.List;

import core.jdbc.JdbcTemplate;

import jwp.model.User;


public class UserDao {
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public void insert(User user) {
        String sql = "INSERT INTO Users VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, preparedStatement -> {
            preparedStatement.setString(1, user.getUserId());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getEmail());
        });
    }

    public User findByUserId(String userId) {

        String sql = "SELECT * FROM Users WHERE userId = ?";
        return (User) jdbcTemplate.queryForObject(sql, preparedStatement -> preparedStatement.setString(1, userId),
                resultSet -> new User(resultSet.getString("userId"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getString("email")));
    }

    public void update(User user) {
        String sql = "UPDATE Users SET password=?, name=?, email=?";
        jdbcTemplate.update(sql, preparedStatement -> {
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getEmail());
        });
    }

    public List<User> findAll(){

        String sql = "SELECT * FROM Users";
        return (List<User>) jdbcTemplate.query(sql, preparedStatement -> {},
                resultSet -> new User(
                        resultSet.getString("userId"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getString("email")
                ));
    }
}