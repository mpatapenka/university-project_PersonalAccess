package org.diploma.personalaccess.holder;

import org.diploma.personalaccess.bean.Period;

import java.sql.Date;
import java.util.List;

/**
 * Holder which contains period data (configurable by property file).
 * It can retrieve period by specified date
 *
 * @author Maksim Patapenka
 */
public interface PeriodHolder {

    /**
     * Get period by specified date
     *
     * @param date date which contains in period borders
     * @return period
     */
    Period getPeriodByDate(Date date);

    /**
     * Return period specified by current date
     *
     * @return current period
     */
    Period getCurrentPeriod();

    /**
     * Get all periods, specified by configuration
     *
     * @return all periods
     */
    List<Period> getAllPeriods();

    /**
     * Get periods name code. It's equivalent of name, but it
     * code used like a message code from resource bundle.
     * It's need for language translator
     *
     * @return periods name code
     */
    String getPeriodsNameCode();

    /**
     * Get all available years
     *
     * @return list of integer (years)
     */
    List<Integer> getAvailableYears();

}
