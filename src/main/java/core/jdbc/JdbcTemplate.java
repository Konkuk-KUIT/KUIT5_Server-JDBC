package core.jdbc;

import jwp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class JdbcTemplate {

    public void update(){
        try {
            try (Connection connection = ConnectionManager.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(createQuery());) {
                setValues(preparedStatement);
                preparedStatement.executeUpdate();
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public abstract String createQuery();
    public abstract void setValues(PreparedStatement preparedStatement);
}
