package core.jdbc;

import jwp.dao.UserDao;
import jwp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class InsertJdbcTemplate {
    // 의존성 주입을 위해 추상메서드로 구현

    public void insert(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = createQueryForInsert(); // ? <- 파라미터처럼 사용 가능

        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            setValuesForInsert(user, preparedStatement);

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

    public abstract String createQueryForInsert();

    public abstract void setValuesForInsert(User user, PreparedStatement preparedStatement) throws SQLException;
}

