package core.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import core.jdbc.KeyHolder;

public  class JdbcTemplate {

    public void insert(String sql, PreparedStatementSetter preparedStatementSetter) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql); //해당 sql 준비해달라고 DB에게 요청
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

    public void updateWithKeyholder(String sql, PreparedStatementSetter pstmtSetter, core.jdbc.KeyHolder holder) {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmtSetter.setValues(pstmt);
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    holder.setId((int) rs.getLong(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    //public abstract String createQuery();

    //public abstract void setValues(PreparedStatement preparedStatement)throws SQLException;
    public List query(String sql, PreparedStatementSetter preparedStatementSetter, RowMapper rowMapper) throws SQLException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try{
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatementSetter.setValues(preparedStatement);

            resultSet = preparedStatement.executeQuery();
            List result = new ArrayList<>();

            while (resultSet.next()) {
                result.add(rowMapper.mapRow(resultSet));
            }
            return result;
        }finally {
            if(preparedStatement!=null){
                preparedStatement.close();
            }
            if(connection!=null){
                connection.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }

    //public abstract Object mapRow(ResultSet resultSet) throws SQLException;
    public Object queryForObject(String sql, PreparedStatementSetter preparedStatementSetter, RowMapper rowMapper) throws SQLException {
        List result = query(sql,preparedStatementSetter,rowMapper);
        if (result.size() == 0) {
            return null;
        } else {
            return result.get(0);
        }
    }
}
