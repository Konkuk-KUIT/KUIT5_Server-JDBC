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
        //TODO 구현 하기
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "INSERT INTO USERS VALUES(?, ?, ?, ?)";
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, user.getUserId());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(4, user.getName());
                preparedStatement.setString(3, user.getEmail());
            }
        };
        jdbcTemplate.update(sql, preparedStatementSetter);
    }

    public User findByUserId(String userId) throws SQLException {
        //TODO 구현 하기
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "SELECT * FROM USERS WHERE userId = ?";
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, userId);
            }
        };
        RowMapper<User> rowMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet) throws SQLException {
                return new User(resultSet.getString("userId"), resultSet.getString("password"),
                        resultSet.getString("name"), resultSet.getString("email"));
            }
        };

        // 매번 캐스팅 해야함
//        return (User) jdbcTemplate.queryForObject(sql, preparedStatementSetter, rowMapper);
        return jdbcTemplate.queryForObject(sql, preparedStatementSetter, rowMapper);
    }

    public void update(User user) throws SQLException {
        //TODO 구현 하기
        JdbcTemplate updateJdbcTemplate = new JdbcTemplate();
        String sql = "UPDATE Users SET password = ?, name = ?, email = ?";
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, user.getPassword());
                preparedStatement.setString(2, user.getName());
                preparedStatement.setString(3, user.getEmail());
            }
        };

        updateJdbcTemplate.update(sql, preparedStatementSetter);
    }

    public List<User> findAll() throws SQLException {
        //TODO 구현 하기
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT * FROM Users";
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {

            }
        };
        RowMapper<User> rowMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet) throws SQLException {
                return new User(resultSet.getString("userId"), resultSet.getString("password"),
                        resultSet.getString("name"), resultSet.getString("email"));
            }
        };

        // 매번 캐스팅 해야함
//        return (List<User>) jdbcTemplate.query(sql, preparedStatementSetter, rowMapper);
        return jdbcTemplate.query(sql, preparedStatementSetter, rowMapper);
    }
}