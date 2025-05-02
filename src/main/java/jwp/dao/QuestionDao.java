package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import jwp.model.Question;

import java.sql.Timestamp;
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

    public Question insert(Question question){
        String sql = "INSERT INTO QUESTIONS(writer, title, contents, createdDate, countOfAnswer) VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new KeyHolder();

        jdbcTemplate.update(sql, ps ->{
            ps.setString(1, question.getWriter());
            ps.setString(2, question.getTitle());
            ps.setString(3, question.getContents());
            ps.setTimestamp(4, Timestamp.valueOf(question.getCreatedDate()));
            ps.setInt(5, question.getCountOfAnswer());
        }, keyHolder);

        return findById(keyHolder.getId());
    }

    public Question findById(long id){
        String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS WHERE questionId = ?";
        return jdbcTemplate.queryForObject(sql, ps -> ps.setLong(1, id),
                rs->new Question(
                        rs.getLong("questionId"),
                        rs.getString("writer"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getTimestamp("createdDate").toLocalDateTime(),
                        rs.getInt("countOfAnswer")
                ));
    }
}
