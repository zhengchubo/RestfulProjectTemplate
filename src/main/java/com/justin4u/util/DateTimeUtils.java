package com.justin4u.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * com.justin4u.util
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2018-12-22</pre>
 */
public class DateTimeUtils {

    /**
     * Get the timestamp of current.
     * @return milliseconds
     */
    public static long ts() {
        return Instant.now().toEpochMilli();
    }

    /**
     * Get the string of current time.
     * @return format as "2020-02-02T07:37:47.511"
     */
    public static String now() {
        LocalDateTime now = LocalDateTime.now();
        return now.toString();
    }

    /**
     * Get the string of today
     * @return format as "2020-02-02"
     */
    public static String today() {
        return LocalDate.now().toString();
    }

    public static LocalDate date2LocalDate(Date date) {
        if (null == date) {
            return null;
        }
//        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return new java.sql.Date(date.getTime()).toLocalDate();
    }

    public static Date localDate2Date(LocalDate localDate) {
        if (null == localDate) {
            return null;
        }
        return java.sql.Date.valueOf(localDate);
    }

    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        if (null == localDateTime) {
            return null;
        }
        return java.sql.Timestamp.valueOf(localDateTime);
    }

    /**
     * Check if this February has 29 days.
     *
     * @return
     */
    public static boolean isBigFeb() {
        YearMonth thisFeb = YearMonth.of(YearMonth.now().getYear(), Month.FEBRUARY);
        return thisFeb.lengthOfMonth() == 29;
    }

    /**
     * Check if current year is 闰年
     *
     * @return
     */
    public static boolean isLeapYear() {
        return Year.now().isLeap();
    }

    public static String formatDateString(String originStr, DateTimeFormatter formatter) {
        try {
            return LocalDate.parse(originStr, DateTimeFormatter.ISO_LOCAL_DATE).format(formatter);
        } catch (Exception ex) {
            return null;
        }
    }

    public static String formatDateString(String originStr, DateTimeFormatter originFormatter, DateTimeFormatter targetFormatter) {
        try {
            return LocalDate.parse(originStr, originFormatter).format(targetFormatter);
        } catch (Exception ex) {
            return null;
        }
    }

    public static String formatDateString(String originStr) {
        return formatDateString(originStr, DateTimeFormatter.BASIC_ISO_DATE);
    }

    public static String removeDashFromDateString(String dateStr) {
        return formatDateString(dateStr, DateTimeFormatter.ISO_LOCAL_DATE, DateTimeFormatter.BASIC_ISO_DATE);
    }

    public static String addDashForDateString(String dateStr) {
        return formatDateString(dateStr, DateTimeFormatter.BASIC_ISO_DATE, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public static void main(String[] args) {
        System.out.println(ts());
        System.out.println(now());

        // Calculate the period between two dates
        LocalDate date1 = LocalDate.of(2000, 1, 1);
        LocalDate date2 = LocalDate.of(2020, 12, 31);
        Period period = Period.between(date1, date2);
        System.out.println("The gap is " + period.getYears() + " years and " + period.getMonths() + " months and " + period.getDays() + " days.");
    }

}
