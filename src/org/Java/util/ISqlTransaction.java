package org.Java.util;

import java.sql.Connection;

//Функциональный интерфейс для работы с базой данных
public interface ISqlTransaction <T>{
    T execute(Connection connection);
}
