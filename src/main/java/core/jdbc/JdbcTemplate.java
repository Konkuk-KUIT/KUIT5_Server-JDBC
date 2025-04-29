package core.jdbc;

import jwp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {

    public void update(String sql, PreparedStatementSetter preparedStatementSetter) {

        // try-with-resources: 여러 자원을 선언하면 선언된 역순으로 닫힘
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatementSetter.setValues(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public <T> List<T> query(String sql, PreparedStatementSetter preparedStatementSetter, RowMapper<T> rowMapper){

        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatementSetter.setValues(preparedStatement);

            try (ResultSet resultSet = preparedStatement.executeQuery()){
                List<T> result = new ArrayList<>();

                while (resultSet.next()) {
                    result.add(rowMapper.mapRow(resultSet));
                }
                return result;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> T queryForObject(String sql, PreparedStatementSetter preparedStatementSetter, RowMapper<T> rowMapper) {
        List<T> result = query(sql, preparedStatementSetter, rowMapper);
        if (result.isEmpty())
            return null;
        return result.get(0);
    }

    //public abstract String createQuery();
    //public abstract void setValues(PreparedStatement preparedStatement) throws SQLException;
    //public abstract Object mapRow(ResultSet resultSet) throws SQLException;

}
