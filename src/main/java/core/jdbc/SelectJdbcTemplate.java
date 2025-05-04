package core.jdbc;

import jwp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class SelectJdbcTemplate {



    public ArrayList<User> readUsers() throws SQLException {
        ArrayList<User> users = new ArrayList<>();

        String sql = createQuery();

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            setValues(preparedStatement);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    User user = getUser(resultSet);
                    users.add(user);
                }

            }
        }
        return users;
    }


    public User readUser() throws SQLException {
        ArrayList<User> users;
        users = readUsers();
        if (users.size() == 1) {
            return users.getFirst();
        }

        return null;
    }

    public abstract String createQuery();

    public abstract void setValues(final PreparedStatement preparedStatement) throws SQLException;

    private User getUser(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getString("userId"),
                resultSet.getString("password"),
                resultSet.getString("name"),
                resultSet.getString("email"));

    }


}
