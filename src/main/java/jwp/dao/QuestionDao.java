package jwp.dao;

import core.jdbc.JdbcTemplate;
import jwp.model.Question;

import java.util.List;

public class QuestionDao {
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
