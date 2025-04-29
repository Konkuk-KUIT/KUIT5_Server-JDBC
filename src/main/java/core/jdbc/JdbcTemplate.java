package core.jdbc;

import jwp.model.KeyHolder;
import jwp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
    public void update(String sql, PreparedStatementSetter preparedStatementSetter, KeyHolder keyHolder) {
        try (Connection connection = ConnectionManager.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatementSetter.setValues(preparedStatement);
            preparedStatement.executeUpdate();
            if(keyHolder != null) {
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    keyHolder.setKey((Long) rs.getLong(1));
                }
                rs.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> query(String sql, PreparedStatementSetter preparedStatementSetter, RowMapper<T> rowMapper) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatementSetter.setValues(preparedStatement);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                List<T> result = new ArrayList<>();
                while (resultSet.next()) {
                    result.add(rowMapper.mapRow(resultSet));
                }
                return result;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Object queryForObject(String sql, PreparedStatementSetter preparedStatementSetter, RowMapper rowMapper) throws SQLException {
        List result = query(sql, preparedStatementSetter, rowMapper);
        if(result.size() == 0){
            return null;
        }
        return result.get(0);
    }

}
