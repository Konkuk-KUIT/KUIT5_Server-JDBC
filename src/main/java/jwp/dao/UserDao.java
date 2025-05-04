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

    public void insert(User user) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";

        PreparedStatementSetter pstmtSetter = pstmt ->  {
                pstmt.setString(1, user.getUserId());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getName());
                pstmt.setString(4, user.getEmail());
        };

        jdbcTemplate.update(sql, pstmtSetter);
    }

    public User findByUserId(String userId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "SELECT * FROM USERS WHERE userId = ?";

        PreparedStatementSetter pstmtSetter = pstmt ->
                pstmt.setString(1, userId);

        RowMapper rowMapper = rs -> new User(rs.getString("userId"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email"));

        return (User) jdbcTemplate.queryForObject(sql, pstmtSetter, rowMapper);
    }

    public void update(User user) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "UPDATE USERS SET password = ?, name = ?, email = ?";

        PreparedStatementSetter pstmtSetter = pstmt ->  {
                pstmt.setString(1, user.getPassword());
                pstmt.setString(2, user.getName());
                pstmt.setString(3, user.getEmail());
        };

        jdbcTemplate.update(sql, pstmtSetter);
    }

    public List<User> findAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "SELECT * FROM USERS";

        PreparedStatementSetter pstmtSetter = pstmt ->  {};

        RowMapper rowMapper = rs ->  new User(rs.getString("userId"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email"));

        return (List<User>) jdbcTemplate.query(sql, pstmtSetter, rowMapper);
    }
}