package org.Java.util;

import org.Java.Exception.StorageException;
import org.Java.Storage.IConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlUtil {
    private IConnectDB connectDB;

    public SqlUtil(IConnectDB connectDB) {
        this.connectDB = connectDB;
    }

    public void requestSQL(String sqlRequest) {
        requestSQL(sqlRequest, (ps) -> ps.execute());
    }

    public <T> T requestSQL(String sqlRequest, IStrategyPreparation<T> preparation) {
        try (Connection connection = connectDB.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest)) {
            return preparation.execute(preparedStatement);
        } catch (SQLException throwables) {
            throw new StorageException("SQL Exception " + throwables.getMessage());
        }
    }

    public <T> T sqlTransaction(ISqlTransaction<T> transaction) {
        try (Connection connection = connectDB.connection();) {
            try {
                connection.setAutoCommit(false);
                T result = transaction.execute(connection);
                connection.commit();
                return result;
            } catch (SQLException throwables) {
                connection.rollback();
                throw throwables;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}

