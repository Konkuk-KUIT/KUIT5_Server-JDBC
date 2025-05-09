package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import jwp.model.User;
import org.springframework.dao.EmptyResultDataAccessException;

public class UserDao {

    public void insert(User user) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, user.getUserId());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getName());
                preparedStatement.setString(4, user.getEmail());
            }
        };
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, preparedStatementSetter);
    }

    public void update(User user) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, user.getPassword());
                preparedStatement.setString(2, user.getName());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getUserId());
            }
        };
        String sql = "UPDATE USERS SET password = ?, name = ?, email = ? WHERE userId = ?";
        jdbcTemplate.update(sql, preparedStatementSetter);
    }

    public User findByUserId(String userId) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, userId);
            }
        };
        RowMapper<User> rowMapper = new RowMapper<>() {
            @Override
            public User mapRow(ResultSet resultSet) throws SQLException {
                return new User(resultSet.getString("userId"), resultSet.getString("password"),
                        resultSet.getString("name"), resultSet.getString("email")
                );
            }
        };
        String sql = "SELECT userId, password, name, email FROM USERS WHERE userId=?";
        try {
            return jdbcTemplate.queryForObject(sql, preparedStatementSetter, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("결과가 없음: " + e.getMessage());
            return null;
        }
    }

    public List<User> findAll() throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        RowMapper<User> rowMapper = new RowMapper<>() {
            @Override
            public User mapRow(ResultSet resultSet) throws SQLException {
                return new User(resultSet.getString("userId"), resultSet.getString("password"),
                        resultSet.getString("name"), resultSet.getString("email")
                );
            }
        };
        String sql = "SELECT userId, password, name, email FROM USERS";
        return jdbcTemplate.query(sql,
                preparedStatement -> {
                }, rowMapper);
    }
}