package org.Java.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface IStrategyPreparation<T> {
      T execute(PreparedStatement preparedStatement) throws SQLException;
}
