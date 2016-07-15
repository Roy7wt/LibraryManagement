package com.roy7wt.service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by apple on 16/5/17.
 */
public class StringService {


    // 加前后缀
    public static String Concat(String value, String fix) {
        return fix + value + fix;
    }

    // 加前缀
    public static String Precat(String value, String fix) {
        return fix + value;
    }

    // 加后缀
    public static String Sufcat(String value, String fix) {
        return value + fix;
    }

    // 在bookEntity.setBorrowDate 和 setReturnDate 时候用
    public static String dateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    // 是否过期
    public static boolean isOverdue(Date date) {
        Calendar calendar = Calendar.getInstance();
        Date currentDate = new Date(calendar.getTimeInMillis());

        if (date.before(currentDate)) {
            return true;
        } else return false;
    }


}
