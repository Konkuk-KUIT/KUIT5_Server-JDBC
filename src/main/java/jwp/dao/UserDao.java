package jwp.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import core.jdbc.ConnectionManager;
import core.jdbc.JdbcTemplate;
import core.jdbc.SelectJdbcTemplate;
import jwp.model.User;

public class UserDao {
    public void insert(User user) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            public String createQuery() {
                return "INSERT INTO Users VALUES (?, ?, ?, ?)";
            }

            @Override
            public void setValues(PreparedStatement preparedStatement) {
                try {
                    preparedStatement.setString(1, user.getUserId());
                    preparedStatement.setString(2, user.getPassword());
                    preparedStatement.setString(3, user.getName());
                    preparedStatement.setString(4, user.getEmail());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        jdbcTemplate.update();
    }


    public User findByUserId(String userId) throws SQLException {

        SelectJdbcTemplate jdbcTemplate = new SelectJdbcTemplate() {
            @Override
            public String createQuery() {
                return "SELECT * FROM Users WHERE userId = ?";
            }

            @Override
            public void setValues(PreparedStatement preparedStatement) {
                try {
                    preparedStatement.setString(1, userId);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }

            @Override
            public Object mapRow(ResultSet resultSet) {
                try {
                    return new User(resultSet.getString("userId"),
                            resultSet.getString("password"),
                            resultSet.getString("name"),
                            resultSet.getString("email"));

                }catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        return jdbcTemplate.readOne();
    }


    public void update(User user) throws SQLException {

        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            public String createQuery() {
                return "UPDATE Users SET password = ?, name = ?, email = ?";
            }

            @Override
            public void setValues(PreparedStatement preparedStatement) {
                try {
                    preparedStatement.setString(1, user.getPassword());
                    preparedStatement.setString(2, user.getName());
                    preparedStatement.setString(3, user.getEmail());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        jdbcTemplate.update();
    }


    public List<User> findAll() throws SQLException {

        SelectJdbcTemplate jdbcTemplate = new SelectJdbcTemplate() {
            @Override
            public String createQuery() {
                return "SELECT * FROM Users";
            }

            @Override
            public void setValues(PreparedStatement preparedStatement) {
            }

            @Override
            public Object mapRow(ResultSet resultSet) {
                try {
                    return new User(resultSet.getString("userId"),
                            resultSet.getString("password"),
                            resultSet.getString("name"),
                            resultSet.getString("email"));

                }catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        };

        return jdbcTemplate.readAll();
    }
}