package jwp.dao;

import core.jdbc.JdbcTemplate;
import jwp.model.Question;

import java.util.List;

public class QuestionDao {
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public List<Question> findAll() {
        String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS ORDER BY questionId DESC";
        return jdbcTemplate.query(sql,
                ps -> {},
                rs -> new Question(
                        rs.getLong("questionId"),
                        rs.getString("writer"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getTimestamp("createdDate").toLocalDateTime(),
                        rs.getInt("countOfAnswer")
                ));
    }
}
