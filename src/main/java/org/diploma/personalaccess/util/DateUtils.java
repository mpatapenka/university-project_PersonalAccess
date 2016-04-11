package org.diploma.personalaccess.util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Util class for working with date, time, etc
 *
 * @author Maksim Patapenka
 */
public final class DateUtils {

    /**
     * Day.Month.Year formatter
     */
    public static final SimpleDateFormat D_M_Y_FORMATTER = new SimpleDateFormat("dd.MM.yyyy");

    /**
     * Day.Month formatter
     */
    public static final SimpleDateFormat D_M_FORMATTER = new SimpleDateFormat("dd.MM");


    /**
     * Can not be instantiated
     */
    private DateUtils() { }


    /**
     * Get today date
     *
     * @return today date (java.sql.Date)
     */
    public static Date today() {
        Calendar calendar = Calendar.getInstance();
        return new Date(calendar.getTime().getTime());
    }

    /**
     * Get current year
     *
     * @return current year
     */
    public static int currentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * Get year from date
     *
     * @param date date
     * @return year from date
     */
    public static int getYearByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

}
