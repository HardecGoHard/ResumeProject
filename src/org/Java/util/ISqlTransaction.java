package org.Java.util;

import java.sql.Connection;

public interface ISqlTransaction <T>{
    T execute(Connection connection);
}
