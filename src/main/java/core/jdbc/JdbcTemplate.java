package core.jdbc;

import jwp.dao.KeyHolder;
import jwp.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
    public void update(String sql, PreparedStatementSetter preparedStatementSetter, KeyHolder keyHolder) throws SQLException {
        //TODO 구현 하기
        try (
                Connection connection =ConnectionManager.getConnection() ;
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ){
            preparedStatementSetter.setValues(preparedStatement);
            preparedStatement.executeUpdate();

            try(ResultSet rs = preparedStatement.getGeneratedKeys()){
                if(rs.next()){
                    keyHolder.setId(rs.getInt(1));
                }
            }

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
        if(result.size()==0){
            return null;
        }
        return result.get(0);
    }
}
