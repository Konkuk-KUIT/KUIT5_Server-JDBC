package core.jdbc;

import jwp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class JdbcTemplate {

    public void update() throws SQLException{

        String sql = createQuery();
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);)
        {
            setValues(preparedStatement);
            preparedStatement.executeUpdate();
        }

    }

    public abstract String createQuery();
    public abstract void setValues(PreparedStatement preparedStatement) throws SQLException;
}
