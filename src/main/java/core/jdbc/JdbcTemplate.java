package core.jdbc;

import jwp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
    public void update(String sql, PreparedStatementSetter preparedStatementSetter) throws SQLException {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//
//        try {
//            connection = ConnectionManager.getConnection();
//            preparedStatement = connection.prepareStatement(sql);
//
////            preparedStatement.setString(1, user.getUserId());
//            preparedStatementSetter.setValues(preparedStatement);
//            preparedStatement.executeUpdate();
//        } finally {
//            if (preparedStatement != null) {
//                preparedStatement.close();
//            }
//            if (connection != null) {
//                connection.close();
//            }
//        }

        Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatementSetter.setValues(preparedStatement);
        try (preparedStatement) {
            preparedStatement.executeUpdate();
        }
    }

    public List query(String sql, PreparedStatementSetter preparedStatementSetter, RowMapper rowMapper) throws SQLException{
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//
//        try {
//            connection = ConnectionManager.getConnection();
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatementSetter.setValues(preparedStatement);
//
//            // select할 때 실행되어야 하는것.
//            resultSet = preparedStatement.executeQuery();
//            List result = new ArrayList<>();
//            while (resultSet.next()) {
//                result.add(rowMapper.mapRow(resultSet));
//            }
//            return result;
//        } finally {
//            if (resultSet != null) {
//                resultSet.close();
//            }
//            if (preparedStatement != null) {
//                preparedStatement.close();
//            }
//            if (connection != null) {
//                connection.close();
//            }
//        }

        Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatementSetter.setValues(preparedStatement);

        try (preparedStatement) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(rowMapper.mapRow(resultSet));
            }
            return result;
        }
    }

    public Object queryForObject(String sql, PreparedStatementSetter preparedStatementSetter, RowMapper rowMapper) throws SQLException {
        List result = query(sql, preparedStatementSetter, rowMapper);
        if (result.size() == 0) {
            return null;
        }
        return result.get(0);
    }

//    public abstract void setValues(PreparedStatement preparedStatement) throws SQLException;
//
//    public abstract String createQuery();
//
//    public abstract Object mapRow(ResultSet resultSet) throws SQLException;
}
