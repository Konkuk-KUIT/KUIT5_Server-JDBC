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
        }finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }if (connection != null) {
                connection.close();
            }
        }
    }

    public List query() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = createQuery();
        ResultSet resultSet = null;

        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            setValues(preparedStatement);
            resultSet = preparedStatement.executeQuery();

            List result = new ArrayList<>();

            User user = null;
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
        }
    }

    public Object queryForObject() throws SQLException {
        List result = query();
        if (result.isEmpty()) return null;

        return result.get(0);
    }

    public abstract String createQuery();

    public abstract void setValues(PreparedStatement preparedStatement) throws SQLException;

    public abstract Object mapRow(ResultSet resultSet) throws SQLException;
}
