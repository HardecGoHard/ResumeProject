package org.Java.util;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

public class DateUtil {
    //Утильный метод для работы с датой.
    public static final LocalDate NOW = LocalDate.of(3000,1,1);
    public static final LocalDate EMPTY = LocalDate.of(0,1,1);

    public static LocalDate of(int year, Month month){
        return LocalDate.of(year, month,1);
    }
    public static LocalDate ParseDate(String string ){
        String[] dates = string.trim().split("\\.");
        System.out.println(Arrays.toString(dates));
        return LocalDate.of(Integer.parseInt(dates[2]),Integer.parseInt(dates[1]),Integer.parseInt(dates[0]));
    }
}
