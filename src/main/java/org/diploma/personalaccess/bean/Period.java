package org.diploma.personalaccess.bean;

import org.springframework.core.style.ToStringCreator;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Simple bean object representing an period.
 *
 * @author Maksim Patapenka
 */
public class Period implements Comparable<Period> {

    /**
     * Period start day. As default it should be 'zero'
     */
    private int startDay;

    /**
     * Period start month.
     */
    private int startMonth;

    /**
     * Period end day. If now end of month make sure you have correct last day
     * of month. '30' or '31', don't remember about february '28' or '29' days.
     * It may be configured for PeriodHolder by property file
     */
    private int endDay;

    /**
     * Period end month
     */
    private int endMonth;



    public int getStartDay() {
        return startDay;
    }

    public void setStartDay(int startDay) {
        this.startDay = startDay;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(int startMonth) {
        this.startMonth = startMonth;
    }

    public int getEndDay() {
        return endDay;
    }

    public void setEndDay(int endDay) {
        this.endDay = endDay;
    }

    public int getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(int endMonth) {
        this.endMonth = endMonth;
    }



    public Date getStartDateForYear(int year) {
        return Date.valueOf(year + "-" + getStartMonth() + "-" + getStartDay());
    }

    public Date getEndDateForYear(int year) {
        return Date.valueOf(year + "-" + getEndMonth() + "-" + getEndDay());
    }

    public String getDateString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        return formatter.format(getStartDateForYear(thisYear)) + " - "
                + formatter.format(getEndDateForYear(thisYear));
    }



    @Override
    public int compareTo(Period o) {
        return Integer.compare(this.startMonth, o.startMonth);
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("startDay", startDay)
                .append("startMonth", startMonth)
                .append("endDay", endDay)
                .append("endMonth", endMonth)
                .toString();
    }

}
