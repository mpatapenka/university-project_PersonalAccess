package org.diploma.personalaccess.util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class DateUtils {

    /**
     * Day.Month.Year formatter
     */
    public static final SimpleDateFormat D_M_Y_FORMATTER = new SimpleDateFormat("dd.MM.yyyy");

    /**
     * Day.Month formatter
     */
    public static final SimpleDateFormat D_M_FORMATTER = new SimpleDateFormat("dd.MM");


    private DateUtils() { }


    public static Date today() {
        Calendar calendar = Calendar.getInstance();
        return new Date(calendar.getTime().getTime());
    }

    public static int currentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static int getYearByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

}
