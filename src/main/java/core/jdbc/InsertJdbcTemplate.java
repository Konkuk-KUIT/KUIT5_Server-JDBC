package core.jdbc;

import jwp.dao.UserDao;
import jwp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertJdbcTemplate {
    public void insert(User user, UserDao userDao) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = userDao.createQueryForInsert();
        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            userDao.setValuesForInsert(user, preparedStatement);
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
}
