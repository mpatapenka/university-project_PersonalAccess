package org.diploma.personalaccess.service.holder;

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
     * Get all periods, specified by configuration
     *
     * @return all periods
     */
    List<Period> getAllPeriods();

}
