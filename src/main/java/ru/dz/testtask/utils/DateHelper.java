package ru.dz.testtask.utils;

import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 16.03.18
 *
 * @author Kuznetsov Maxim
 */
public class DateHelper {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static String getFormattedDate() {
        return dateFormat.format(new Date());
    }

    @SneakyThrows
    public static Date getNextDay() {
        Calendar c = Calendar.getInstance();
        Date date = dateFormat.parse(getFormattedDate());
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        return c.getTime();
    }
}
