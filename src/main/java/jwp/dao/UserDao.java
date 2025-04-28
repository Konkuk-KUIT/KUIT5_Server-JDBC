package jwp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.jdbc.ConnectionManager;
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
        };

        jdbcTemplate.update();

    }

    public User findByUserId(String userId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM Users WHERE userId = ?";

        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userId);
            // resultSet: select문 날렸을 때 DB가 준 결과 담는 곳
            resultSet = preparedStatement.executeQuery();

            User user = null;
            if (resultSet.next()) {
                user = new User(resultSet.getString("userId"), resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getString("email"));
                return user;
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }

        return null;
    }

    public void update(User user) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            public String createQuery() {
                return "UPDATE Users SET password = ? ,name = ?, email = ? WHERE userId = ?";
            }

            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, user.getPassword());
                preparedStatement.setString(2, user.getName());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getUserId());
            }
        };

        jdbcTemplate.update();
    }

    public List<User> findAll() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<User> users = new ArrayList<>();
        ResultSet resultSet = null;

        String sql = "SELECT * FROM Users";

        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User(resultSet.getString("userId"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getString("email"));

                users.add(user);
            }

        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
        return users;
    }
}