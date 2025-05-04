package core.jdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {

    public void update(String sql, PreparedStatementSetter preparedStatementSetter) {
        try {
            try (Connection connection = ConnectionManager.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
                preparedStatementSetter.setValues(preparedStatement);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public <T> List<T> query(String sql, PreparedStatementSetter preparedStatementSetter, RowMapper rowMapper) {
        ArrayList<T> result = new ArrayList<>();
        try {
            try (Connection connection = ConnectionManager.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
                preparedStatementSetter.setValues(preparedStatement);
                try (ResultSet resultSet = preparedStatement.executeQuery();) {
                    while (resultSet.next()) {
                        result.add((T)rowMapper.mapRow(resultSet));
                    }

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    public Object queryForObject(String sql, PreparedStatementSetter preparedStatementSetter, RowMapper rowMapper) {
        List result = query(sql, preparedStatementSetter, rowMapper);
        if (result.size() == 0)
            return null;

        return result.getFirst();
    }


}
