package ru.dz.testtask.utils;

import lombok.SneakyThrows;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 16.03.18
 *
 * @author Kuznetsov Maxim
 */
public class DateHelper {

    private static FastDateFormat fastDateFormat = FastDateFormat.getInstance("yyyy-MM-dd");

    public static String getFormattedCurrentDate() {
        return fastDateFormat.format(new Date());
    }

    @SneakyThrows
    public static Date getDate(String date) {
        return fastDateFormat.parse(date);
    }

    @SneakyThrows
    public static List<String> getFormattedPeriodDates(String startDate, String endDate) {
        Date start = fastDateFormat.parse(startDate);
        Date end = fastDateFormat.parse(endDate);

        List<String> dates = new ArrayList<>();
        dates.add(startDate);

        while (!DateUtils.isSameDay(start, end)) {
            start = DateUtils.addDays(start, 1);

            dates.add(fastDateFormat.format(start));
        }
        return dates;
    }
}
