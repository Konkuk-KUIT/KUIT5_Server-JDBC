package core.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class SelectJdbcTemplate {
    public List query() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionManager.getConnection();
            String sql = createQuery();

            preparedStatement = connection.prepareStatement(sql);

            setValues(preparedStatement);
            resultSet= preparedStatement.executeQuery();
            List result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(mapRow(resultSet));
            }
            return result;
        }finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }

    public Object queryForObject() throws SQLException{
        List result = query();
        if (result.size()==0)
            return null;
        return result.get(0);
    }
    public abstract String createQuery();
    public abstract void setValues(PreparedStatement preparedStatement) throws SQLException;
    public abstract Object mapRow(ResultSet resultSet)throws SQLException;


}
