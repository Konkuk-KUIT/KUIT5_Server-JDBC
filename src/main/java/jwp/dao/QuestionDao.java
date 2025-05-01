package jwp.dao;

import core.jdbc.ConnectionManager;
import jwp.model.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDao {
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
