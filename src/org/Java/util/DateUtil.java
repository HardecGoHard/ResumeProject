package org.Java.util;

import java.time.LocalDate;
import java.time.Month;

public class DateUtil {
    //Утильный метод для работы с датой.
    public static final LocalDate NOW = LocalDate.of(3000,1,1);
    public static LocalDate of(int year, Month month){
        return LocalDate.of(year, month,1);
    }
}
