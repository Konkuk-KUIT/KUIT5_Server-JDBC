package core.jdbc;

import jwp.model.User;
import org.springframework.dao.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
    // INSERT, UPDATE, DELETE
    public void update(String sql, PreparedStatementSetter preparedStatementSetter) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatementSetter.setValues(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // SELECT ; 복수 row
    public <T> List<T> query(String sql, PreparedStatementSetter preparedStatementSetter, RowMapper<T> rowMapper){
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatementSetter.setValues(preparedStatement);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<T> result = new ArrayList<>();
                while (resultSet.next()) {
                    result.add(rowMapper.mapRow(resultSet));
                }
                return result;
            }
        } catch (SQLException e) {
            // SQLException : checked exception(반드시 catch하거나 throws해야 하는 예외)
            // 그래서 unchecked exception의 한 종류인 RuntimeException으로 바꿔서 던지기
            throw new RuntimeException(e);
        }
    }

    // SELECT ; 단일 row
    public <T> T queryForObject(String sql, PreparedStatementSetter preparedStatementSetter, RowMapper rowMapper) {
        List<T> result = query(sql, preparedStatementSetter, rowMapper);
        if (result.size() == 0) {
            return null;
        }
        return result.get(0);
    }
}
