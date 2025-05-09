package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;

import java.util.List;

public class QuestionDao {
    public Question insert(Question question) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) VALUES (?, ?, ?, ?, ?)";

        KeyHolder holder = new KeyHolder();
        jdbcTemplate.update(sql, ps -> {
            ps.setString(1, question.getWriter());
            ps.setString(2, question.getTitle());
            ps.setString(3, question.getContents());
            ps.setTimestamp(4, new java.sql.Timestamp(question.getCreatedDate().getTime()));
            ps.setInt(5, question.getCountOfAnswer());
        }, holder);

        return findById(holder.getId());
    }

    public Question findById(int id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT * FROM QUESTIONS WHERE questionId = ?";
        return jdbcTemplate.queryForObject(sql, ps -> ps.setInt(1, id),
                rs -> new Question(
                        rs.getLong("questionId"),
                        rs.getString("writer"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getTimestamp("createdDate"),
                        rs.getInt("countOfAnswer")
                )
        );
    }

    public List<Question> findAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT * FROM QUESTIONS ORDER BY questionId DESC";
        return jdbcTemplate.query(sql, ps -> {},
                rs -> new Question(
                        rs.getLong("questionId"),
                        rs.getString("writer"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getTimestamp("createdDate"),
                        rs.getInt("countOfAnswer")
                )
        );
    }
}
