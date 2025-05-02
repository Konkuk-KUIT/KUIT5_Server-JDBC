package core.jdbc;

import jwp.model.KeyHolder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {

    public void update(String sql, PreparedStatementSetter preparedStatementSetter) {
        try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            preparedStatementSetter.setValues(pstmt);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(String sql, PreparedStatementSetter pstmtSetter, KeyHolder holder) {
        try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmtSetter.setValues(pstmt);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                holder.setId(rs.getLong(1));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List query(String sql, PreparedStatementSetter preparedStatementSetter, RowMapper rowMapper) {
        try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            preparedStatementSetter.setValues(pstmt);

            try (ResultSet resultSet = pstmt.executeQuery()) {
                List results = new ArrayList<>();
                while(resultSet.next()) {
                    results.add(rowMapper.mapRow(resultSet));
                }
                return results;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Object queryForObject(String sql, PreparedStatementSetter preparedStatementSetter, RowMapper rowMapper) {
        List result = query(sql, preparedStatementSetter, rowMapper);
        if (result.isEmpty())
            return null;
        return result.get(0);
    }
}
