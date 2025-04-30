package core.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {

    public void update(String sql, PreparedStatementSetter preparedStatementSetter, KeyHolder holder) {

        // try-with-resources: 여러 자원을 선언하면 선언된 역순으로 닫힘
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatementSetter.setValues(preparedStatement);
            preparedStatement.executeUpdate();

            if (holder != null) {
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    holder.setId(rs.getLong(1));
                }
                rs.close();
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
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
