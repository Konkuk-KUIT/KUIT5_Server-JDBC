package core.jdbc;

import jwp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
    public void update(String sql, PreparedStatementSetter preparedStatementSetter, KeyHolder keyHolder) throws SQLException {

        try (
                Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatementSetter.setValues(preparedStatement);
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                keyHolder.setId((int) rs.getLong(1));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(String sql, PreparedStatementSetter preparedStatementSetter) {
        try (
                Connection conn = ConnectionManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            preparedStatementSetter.setValues(pstmt);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> query(String sql, PreparedStatementSetter preparedStatementSetter, RowMapper rowMapper) throws SQLException {
        try (
                Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatementSetter.setValues(preparedStatement);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List result = new ArrayList<>();
                while (resultSet.next()) {
                    result.add(rowMapper.mapRow(resultSet));
                }
                return result;
            }
        }

    }

    public <T> T queryForObject(String sql, PreparedStatementSetter preparedStatementSetter, RowMapper rowMapper) throws SQLException {
        List<T> result = query(sql, preparedStatementSetter, rowMapper);
        if (result.isEmpty())
            return null;
        return result.get(0);
    }
}
