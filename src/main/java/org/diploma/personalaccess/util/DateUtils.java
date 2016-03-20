package org.diploma.personalaccess.util;

import java.sql.Date;
import java.util.Calendar;

public final class DateUtils {

    private DateUtils() { }

    public static Date today() {
        Calendar calendar = Calendar.getInstance();
        return new Date(calendar.getTime().getTime());
    }

}
