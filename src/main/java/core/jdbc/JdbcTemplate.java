package core.jdbc;

import jwp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class JdbcTemplate {
    public void update() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = createQuery();

        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            setValues(preparedStatement);
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

    public List query() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        try {
            List result = new ArrayList<>();
            String sql = createQuery();

            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            setValues(preparedStatement);

            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                result.add(mapRow(resultSet));
            }
            return result;

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

    public Object queryForObject() throws SQLException {
        List result = query();
        if (result.isEmpty())
            return null;
        return result.get(0);

    }

    public abstract Object mapRow(ResultSet resultSet) throws SQLException;

    public abstract String createQuery();

    public abstract void setValues(PreparedStatement preparedStatement) throws SQLException;
}
