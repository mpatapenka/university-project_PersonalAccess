package org.diploma.personalaccess.holder.impl;

import org.apache.log4j.Logger;
import org.diploma.personalaccess.bean.Period;
import org.diploma.personalaccess.holder.PeriodHolder;
import org.diploma.personalaccess.util.DateUtils;
import org.diploma.personalaccess.util.PeriodParser;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.*;

/**
 * In memory implementation of period holder. Configured by .properties file
 *
 * @author Maksim Patapenka
 */
@Component
public class PeriodHolderImpl implements PeriodHolder {

    /**
     * Logger Log4j
     */
    private static final Logger log = Logger.getLogger(PeriodHolderImpl.class);


    /**
     * Default filename of property file
     */
    private static final String HOLDER_CONFIG_FILE = "periods.properties";

    /**
     * Default period name code. It's a quarter
     */
    private static final String DEFAULT_PERIOD_NAME_CODE = "QUARTER";

    /**
     * Default period count. For quarters it's '4'
     */
    private static final String DEFAULT_PERIOD_COUNT = "4";

    /**
     * Default start year
     */
    private static final String DEFAULT_START_YEAR = "2015";

    /**
     * Default end year
     */
    private static final String DEFAULT_END_YEAR = "2025";


    /**
     * List which contains all period. In memory holder
     */
    private List<Period> entries;

    /**
     * Period name code. Code using for index for translation on page
     */
    private String periodNameCode;

    /**
     * Available years
     */
    private List<Integer> years;


    /**
     * Period holder impl initializer
     */
    public PeriodHolderImpl() {
        entries = new ArrayList<>();
        years = new ArrayList<>();

        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(HOLDER_CONFIG_FILE);
            Properties properties = new Properties();
            properties.load(is);


            periodNameCode = properties.getProperty("code", DEFAULT_PERIOD_NAME_CODE);
            final int periodsCount = Integer.parseInt(properties.getProperty("count", DEFAULT_PERIOD_COUNT));
            for (int i = 0; i < periodsCount; i++) {
                String key = createPeriodKey(i);
                String periodData = properties.getProperty(key);
                Period period = PeriodParser.convertPeriodDataStringToPeriod(periodData);
                entries.add(period);
            }

            Collections.sort(entries);

            final int startYear = Integer.parseInt(properties.getProperty("startYear", DEFAULT_START_YEAR));
            final int endYear = Integer.parseInt(properties.getProperty("endYear", DEFAULT_END_YEAR));

            for (int i = startYear; i <= endYear; i++) {
                years.add(i);
            }

        } catch (IOException e) {
            String msg = "Can not find property file '" + HOLDER_CONFIG_FILE + "' in classpath.";
            log.error(msg, e);
            throw new IllegalArgumentException(msg, e);
        } catch (NumberFormatException e) {
            String msg = "Check property config file. Any fields have incorrect values.";
            log.error(msg, e);
            throw new IllegalArgumentException(msg, e);
        }
    }


    /**
     * Create period key (like property file key). To period index add
     * default prefix (default is 'p'). If will be switched prefix in property
     * file, then prefix must be switched here too
     *
     * @param index index of period
     * @return period key
     */
    private static String createPeriodKey(int index) {
        /* Default key prefix */
        final String keyPrefix = "p";
        return keyPrefix + index;
    }


    @Override
    public Period getPeriodByDate(Date date) {
        int year = DateUtils.getYearByDate(date);

        for (Period period : entries) {
            Date start = period.getStartDateForYear(year);
            Date end = period.getEndDateForYear(year);

            if (date.compareTo(start) >= 0 /* date after or equal start date */
                    && date.compareTo(end) <= 0 /* date before or equal end date */) {
                return period;
            }
        }

        String msg = "No one period found for today '" + date
                + "', please reconfigure PeriodHolder so all days should be in any period.";
        log.error(msg);
        throw new IllegalArgumentException(msg);
    }

    @Override
    public Period getCurrentPeriod() {
        return getPeriodByDate(DateUtils.today());
    }

    @Override
    public List<Period> getAllPeriods() {
        return entries;
    }

    @Override
    public String getPeriodsNameCode() {
        return periodNameCode;
    }

    @Override
    public List<Integer> getAvailableYears() {
        return years;
    }

    @Override
    public Period getPeriodById(long id) {
        if (id >= 0 && id < entries.size()) {
            return entries.get((int) id);
        }

        String msg = "Unsupported id '" + id + "'!";
        log.error(msg);
        throw new IllegalArgumentException(msg);
    }

    @Override
    public long getIdOfPeriod(Period period) {
        for (int i = 0; i < entries.size(); i++) {
            Period entry = entries.get(i);
            if (entry.equals(period)) {
                return i;
            }
        }

        String msg = "Unsupported period '" + period + "'!";
        log.error(msg);
        throw new IllegalArgumentException(msg);
    }

}
