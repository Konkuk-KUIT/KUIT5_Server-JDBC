package core.jdbc;

import jwp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class JdbcTemplate {

    public void update() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            String sql = createQuery();
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            setValues(preparedStatement);
            preparedStatement.executeUpdate();

        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }
    }
    public abstract String createQuery();
    public abstract void setValues(PreparedStatement preparedStatement) throws SQLException;
}
