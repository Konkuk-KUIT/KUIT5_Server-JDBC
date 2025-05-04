package core.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {

    public void update(String sql, PreparedStatementSetter pstmtSetter)  {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmtSetter.setValues(pstmt);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List query(String sql, PreparedStatementSetter pstmtSetter, RowMapper rowMapper) {
        ResultSet rs = null;
        List result = new ArrayList<>();

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmtSetter.setValues(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                result.add(rowMapper.mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public Object queryForObject(String sql, PreparedStatementSetter pstmtSetter, RowMapper rowMapper) {
        List result = query(sql, pstmtSetter, rowMapper);
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);

    }

}
