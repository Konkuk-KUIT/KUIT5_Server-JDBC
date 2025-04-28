package core.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
    public void update(String sql, PreparedStatementSetter preparedStatementSetter) throws SQLException {
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ){
            preparedStatementSetter.setValues(preparedStatement);
            preparedStatement.executeUpdate();
        }
    }

    public void update(String sql, PreparedStatementSetter pstmtSetter, KeyHolder holder) {
        try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmtSetter.setValues(pstmt);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                holder.setId((int) rs.getLong(1));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> query(String sql, PreparedStatementSetter preparedStatementSetter, RowMapper<T> rowMapper) throws SQLException {
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ){
            preparedStatementSetter.setValues(preparedStatement);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                List<T> result  = new ArrayList<>();
                while(resultSet.next()){
                    result.add(rowMapper.mapRow(resultSet));
                }

                return result;
            }
        }
    }

    public <T> T queryForObject(String sql, PreparedStatementSetter preparedStatementSetter, RowMapper<T> rowMapper)throws SQLException {
        List<T> result = query(sql,preparedStatementSetter,rowMapper);
        if(result.size()==0){
            return null;
        }
        return result.get(0);
    }
}
