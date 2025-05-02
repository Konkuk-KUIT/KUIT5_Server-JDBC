package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import jwp.model.User;

import java.util.List;

public class UserDao {
    public void insert(User user) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "INSERT INTO Users VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new KeyHolder();

        jdbcTemplate.update(sql, ps -> {
            ps.setString(1, user.getUserId());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setString(4, user.getEmail());
        }, keyHolder);
    }

    public User findByUserId(String userId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT * FROM Users WHERE userId = ?";
        return jdbcTemplate.queryForObject(sql, ps -> ps.setString(1, userId),
                rs -> new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs.getString("email"))
        );
    }

    public void update(User user) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "UPDATE Users SET password = ?, name = ?, email = ?";
        KeyHolder keyHolder = new KeyHolder();

        jdbcTemplate.update(sql, ps -> {
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getName());
            ps.setString(3, user.getEmail());
        }, keyHolder);
    }

    public List<User> findAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT * FROM Users";
        return jdbcTemplate.query(sql, ps -> {
                },
                rs -> new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs.getString("email"))
        );
    }
}