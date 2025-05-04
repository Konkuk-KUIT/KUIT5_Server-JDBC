package jwp.dao;

import java.util.List;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.User;

public class UserDao {
    private final JdbcTemplate<User> jdbcTemplate = new JdbcTemplate<>();

    public void insert(User user) {
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
        String sql = "SELECT * FROM USERS WHERE userId = ?";

        PreparedStatementSetter pstmtSetter = pstmt ->
                pstmt.setString(1, userId);

        RowMapper<User> rowMapper = rs -> new User(rs.getString("userId"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email"));

        return jdbcTemplate.queryForObject(sql, pstmtSetter, rowMapper);
    }

    public void update(User user) {
        String sql = "UPDATE USERS SET password = ?, name = ?, email = ?";

        PreparedStatementSetter pstmtSetter = pstmt ->  {
                pstmt.setString(1, user.getPassword());
                pstmt.setString(2, user.getName());
                pstmt.setString(3, user.getEmail());
        };

        jdbcTemplate.update(sql, pstmtSetter);
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM USERS";

        PreparedStatementSetter pstmtSetter = pstmt ->  {};

        RowMapper<User> rowMapper = rs ->  new User(rs.getString("userId"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email"));

        return jdbcTemplate.query(sql, pstmtSetter, rowMapper);
    }
}