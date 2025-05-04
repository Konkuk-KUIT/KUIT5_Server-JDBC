package core.jdbc;

import jwp.model.User;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class SelectJdbcTemplate {

    //Todo 제네릭 구현

    public <T> ArrayList<T> readAll(){
        ArrayList<T> ts = new ArrayList<>();
        try {
            try (Connection connection = ConnectionManager.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(createQuery());) {
                setValues(preparedStatement);
                try (ResultSet resultSet = preparedStatement.executeQuery();) {
                    while (resultSet.next()) {
                        T t = (T)mapRow(resultSet);
                        ts.add(t);
                    }

                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return ts;
    }


    public <T> T readOne() {
        ArrayList<T> ts;
        ts = readAll();
        if (ts.size() == 1) {
            return ts.getFirst();
        }

        return null;
    }

    public abstract String createQuery();

    public abstract void setValues(final PreparedStatement preparedStatement);



    public abstract Object mapRow(ResultSet resultSet);

}
