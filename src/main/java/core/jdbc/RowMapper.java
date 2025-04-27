package core.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper {

    public abstract Object mapRow(ResultSet resultSet) throws SQLException;
}
