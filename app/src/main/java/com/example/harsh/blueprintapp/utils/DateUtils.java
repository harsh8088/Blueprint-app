package com.example.harsh.blueprintapp.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;


public class DateUtils {

    private DateUtils() {
    }

    public static String formatDate(String date, String sourceFormat, String targetFormat) {
        try {
            Date d = new SimpleDateFormat(sourceFormat, Locale.getDefault()).parse(date);
            return new SimpleDateFormat(targetFormat, Locale.getDefault()).format(d);
        } catch (Exception e) {
            LogUtils.printStackTrace(e);
        }
        return null;
    }

    public static String formatDate(String date, String sourceFormat, String targetFormat, TimeZone timezone) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(sourceFormat, Locale.getDefault());
            simpleDateFormat.setTimeZone(timezone);
            Date d = simpleDateFormat.parse(date);
            return new SimpleDateFormat(targetFormat, Locale.getDefault()).format(d);
        } catch (Exception e) {
            LogUtils.printStackTrace(e);
        }
        return null;
    }

    public static String formatDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        return sdf.format(date.getTime());
    }

    public static Date formatDate(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        try {
            return sdf.parse(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static String changeToUTC(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("gmt"));
        return sdf.format(date.getTime());
    }

    public static boolean isLeapYear(int year) {
        return (year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0));
    }

    public static long differenceInDays(Date date, Date compareWith) {
        long diff = compareWith.getTime() - date.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static Date getDays(Date currentDate, int daysToIncreaseOrDecrease) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.DATE, daysToIncreaseOrDecrease);
        return cal.getTime();
    }

    public static Calendar dateToCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static String getRelativeOverdueTime(long timeInMillis) {
        String time = android.text.format.DateUtils
                .getRelativeTimeSpanString(timeInMillis, System.currentTimeMillis(),
                        android.text.format.DateUtils.SECOND_IN_MILLIS).toString();
        return time.replace("ago", "");
    }
}
