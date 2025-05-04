package jwp.dao;

import java.sql.*;
import java.util.List;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.User;

public class UserDao {

    public void insert(User user) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "INSERT INTO Users VALUES (?, ?, ?, ?)";

        PreparedStatementSetter preparedStatementSetter = preparedStatement -> {
            try {
                preparedStatement.setString(1, user.getUserId());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getName());
                preparedStatement.setString(4, user.getEmail());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };

        jdbcTemplate.update(sql, preparedStatementSetter);
    }


    public User findByUserId(String userId) throws SQLException {

        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "SELECT * FROM Users WHERE userId = ?";

        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                try {
                    preparedStatement.setString(1, userId);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        RowMapper rowMapper = new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet) throws SQLException {
                try {
                    return new User(resultSet.getString("userId"),
                            resultSet.getString("password"),
                            resultSet.getString("name"),
                            resultSet.getString("email"));

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        };


        return (User) jdbcTemplate.queryForObject(sql, preparedStatementSetter, rowMapper);
    }


    public void update(User user) throws SQLException {

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "UPDATE Users SET password = ?, name = ?, email = ?";

        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                try {
                    preparedStatement.setString(1, user.getPassword());
                    preparedStatement.setString(2, user.getName());
                    preparedStatement.setString(3, user.getEmail());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        };


        jdbcTemplate.update(sql, preparedStatementSetter);
    }


    public List<User> findAll() throws SQLException {

        JdbcTemplate jdbcTemplate = new JdbcTemplate();


        String sql = "SELECT * FROM Users";

        PreparedStatementSetter preparedStatementSetter = preparedStatement -> {
        };

        RowMapper rowMapper = resultSet -> {
            try {
                return new User(resultSet.getString("userId"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getString("email"));

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };

        return jdbcTemplate.query(sql, preparedStatementSetter, rowMapper);
    }
}