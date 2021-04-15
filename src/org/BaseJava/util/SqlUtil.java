package org.BaseJava.util;

import org.BaseJava.Exception.StorageException;
import org.BaseJava.Storage.IConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlUtil {
    private IConnectDB connectDB;

    public SqlUtil(IConnectDB connectDB) {
        this.connectDB = connectDB;
    }
    public void requestSQL(String sqlRequest){
        requestSQL(sqlRequest, (ps) ->ps.execute() );
    }
    public <T> T requestSQL(String sqlRequest, IStrategyPreparation<T> preparation ){
        try (Connection connection = connectDB.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest)){
            return preparation.execute(preparedStatement);
        } catch (SQLException throwables) {
            throw new StorageException("SQL Exception");
        }
    }
}
