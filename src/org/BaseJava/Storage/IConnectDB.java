package org.BaseJava.Storage;

import java.sql.Connection;
import java.sql.SQLException;

public interface  IConnectDB {
    public Connection connection() throws SQLException;
}
