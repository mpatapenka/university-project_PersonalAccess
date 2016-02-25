package org.diploma.personalaccess.bean;

import java.sql.Date;
import java.util.Calendar;

/**
 * Simple bean object representing an period.
 *
 * @author Maksim Patapenka
 */
public class Period {

    private int number;
    private int startDay;
    private int startMonth;
    private int endDay;
    private int endMonth;
    private String name;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDateForYear(int year) {
        return Date.valueOf(year + ":" + getStartMonth() + ":" + getStartDay());
    }

    public Date getEndDateForYear(int year) {
        return Date.valueOf(year + ":" + getEndMonth() + ":" + getEndDay());
    }

}
