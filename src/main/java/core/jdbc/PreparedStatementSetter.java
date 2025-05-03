package core.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementSetter {

    public abstract void setValues(PreparedStatement preparedStatement) throws SQLException;
}
