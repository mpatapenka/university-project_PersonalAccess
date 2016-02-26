package org.diploma.personalaccess.holder.impl;

import org.apache.log4j.Logger;
import org.diploma.personalaccess.bean.Period;
import org.diploma.personalaccess.holder.PeriodHolder;
import org.diploma.personalaccess.util.Parser;
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
     * Logger Log4j
     */
    private static final Logger log = Logger.getLogger(PeriodHolderImpl.class);



    /**
     * List which contains all period. In memory holder
     */
    private List<Period> entries;

    /**
     * Period name code. Code using for index for translation on page
     */
    private String periodNameCode;



    public PeriodHolderImpl() {
        entries = new ArrayList<>();

        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(HOLDER_CONFIG_FILE);
            Properties properties = new Properties();
            properties.load(is);


            periodNameCode = properties.getProperty("code", DEFAULT_PERIOD_NAME_CODE);
            final int periodsCount = Integer.parseInt(properties.getProperty("count", DEFAULT_PERIOD_COUNT));
            for (int i = 0; i < periodsCount; i++) {
                String key = createPeriodKey(i);
                String periodData = properties.getProperty(key);
                Period period = Parser.convertPeriodDataStringToPeriod(periodData);
                entries.add(period);
            }

            Collections.sort(entries);

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

    private static String createPeriodKey(int index) {
        /* Default key prefix */
        final String keyPrefix = "p";
        return keyPrefix + index;
    }



    @Override
    public Period getPeriodByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);

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
        Calendar calendar = Calendar.getInstance();
        Date today = new Date(calendar.getTime().getTime());
        return getPeriodByDate(today);
    }

    @Override
    public List<Period> getAllPeriods() {
        return entries;
    }

    @Override
    public String getPeriodsNameCode() {
        return periodNameCode;
    }

}
