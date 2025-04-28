package core.jdbc;

import jwp.dao.UserDao;
import jwp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class UpdateJdbcTemplate {
    public void update(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = createQueryForUpdate();

        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            setValuesForUpdate(user, preparedStatement);
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

    public abstract String createQueryForUpdate();

    public abstract void setValuesForUpdate(User user, PreparedStatement preparedStatement) throws SQLException;
}
