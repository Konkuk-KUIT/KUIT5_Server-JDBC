package core.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {

    public void update(String sql, PreparedStatementSetter preparedStatementSetter) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatementSetter.setValues(preparedStatement);

            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public List query(String sql, PreparedStatementSetter preparedStatementSetter, RowMapper rowMapper) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatementSetter.setValues(preparedStatement);

            resultSet = preparedStatement.executeQuery();
            List results = new ArrayList<>();
            while(resultSet.next()) {
                results.add(rowMapper.mapRow(resultSet));
            }
            return results;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public Object queryForObject(String sql, PreparedStatementSetter preparedStatementSetter, RowMapper rowMapper) throws SQLException {
        List result = query(sql, preparedStatementSetter, rowMapper);
        if (result.isEmpty())
            return null;
        return result.get(0);
    }
}
