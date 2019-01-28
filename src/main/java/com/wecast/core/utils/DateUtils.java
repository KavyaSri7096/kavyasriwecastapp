package com.wecast.core.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by ageech@live.com
 */

public final class DateUtils {

    public static String getDay(int day) {
        // Get calendar instance and add day of week
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_WEEK, day);
        // Get day of week
        Date date = calendar.getTime();
        return new SimpleDateFormat("EEEE", Locale.getDefault()).format(date);
    }

    public static String beginOfDay(Date date) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return String.valueOf(calendar.getTimeInMillis() / 1000);
    }

    public static String endOfDay(Date date) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return String.valueOf(calendar.getTimeInMillis() / 1000);
    }
}
