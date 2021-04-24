package org.Java.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;
//Функциональный интерфейс для работы с базой данных
public interface IStrategyPreparation<T> {
      T execute(PreparedStatement preparedStatement) throws SQLException;
}
