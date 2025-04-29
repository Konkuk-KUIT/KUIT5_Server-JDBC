package core.jdbc;

import jwp.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {
    T mapRow(ResultSet resultSet) throws SQLException;
}
