package jwp.dao;

import core.jdbc.JdbcTemplate;
import jwp.model.Answer;

import java.util.List;

public class AnswerDao {
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public List<Answer> findAllByQuestionId(long questionId) {
        String sql = "SELECT answerId, questionId, writer, contents, createdDate FROM Answers WHERE questionId = ? ORDER BY answerId ASC";

        return jdbcTemplate.query(sql,
                ps -> ps.setLong(1, questionId),
                rs -> new Answer(
                        rs.getLong("answerId"),
                        rs.getLong("questionId"),
                        rs.getString("writer"),
                        rs.getString("contents"),
                        rs.getTimestamp("createdDate").toLocalDateTime()
                ));
    }
}
