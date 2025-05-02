package jwp.dao;

import core.jdbc.ConnectionManager;
import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import jwp.model.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDao {
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public Question insert(Question question) throws SQLException {
        String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) VALUES (?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new KeyHolder();

        jdbcTemplate.updateWithKeyholder(sql, pstmt -> {
            pstmt.setString(1, question.getWriter());
            pstmt.setString(2, question.getTitle());
            pstmt.setString(3, question.getContents());
            pstmt.setTimestamp(4, question.getCreatedDate());
            pstmt.setInt(5, question.getCountOfAnswer());
        }, keyHolder);

        return findById((long) keyHolder.getId());
    }

    public Question findById(Long id) throws SQLException {
        String sql = "SELECT * FROM QUESTIONS WHERE questionId = ?";
        return (Question) jdbcTemplate.queryForObject(sql,
                pstmt -> pstmt.setLong(1, id),
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
    public List<Question> findAll() throws SQLException {
        String sql = "SELECT * FROM QUESTIONS ORDER BY questionId DESC";
        List<Question> questions = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                Question q = new Question(
                        rs.getLong("questionId"),
                        rs.getString("writer"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getTimestamp("createdDate"),
                        rs.getInt("countOfAnswer")
                );
                questions.add(q);
            }
        }
        return questions;
    }
}
