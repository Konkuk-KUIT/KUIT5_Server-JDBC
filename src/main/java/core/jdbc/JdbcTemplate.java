package core.jdbc;

import jwp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate{
    public void update(String sql, PreparedStatementSetter preparedStatementSetter) throws SQLException {
        try (Connection connection = ConnectionManager.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatementSetter.setValues(preparedStatement);
            preparedStatement.executeUpdate();
        }
    }

    public <T> List<T> query(String sql, PreparedStatementSetter preparedStatementSetter, RowMapper<T> rowMapper) throws SQLException {
        try (Connection connection = ConnectionManager.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatementSetter.setValues(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<T> result = new ArrayList<>();

            while (resultSet.next()) {
                result.add(rowMapper.mapRow(resultSet));
            }

            return result;
        }
    }

    public <T> T queryForObject(String sql, PreparedStatementSetter preparedStatementSetter, RowMapper<T> rowMapper) throws SQLException {
        List<T> result = query(sql, preparedStatementSetter, rowMapper);
        if (result.isEmpty()) return null;

        return result.get(0);
    }
}
