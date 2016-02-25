package org.diploma.personalaccess.service.holder.impl;

import org.apache.log4j.Logger;
import org.diploma.personalaccess.bean.Period;
import org.diploma.personalaccess.service.holder.PeriodHolder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.*;

/**
 * In memory implementation of period holder. Configured by .properties file
 *
 * @author Maksim Patapenka
 */
public class PeriodHolderImpl implements PeriodHolder {

    /**
     * Default filename of property file
     */
    private static final String HOLDER_CONFIG_FILE = "periods.properties";

    /**
     * Logger Log4j
     */
    private static final Logger log = Logger.getLogger(PeriodHolderImpl.class);

    /**
     * List which contains all period. In memory holder
     */
    private List<Period> entries;

    public PeriodHolderImpl() {
        entries = new ArrayList<>();

        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(HOLDER_CONFIG_FILE);
            Properties properties = new Properties();
            properties.load(is);

        } catch (IOException e) {
            String msg = "Can not find property file '" + HOLDER_CONFIG_FILE + "' in classpath.";
            log.error(msg, e);
            throw new IllegalArgumentException(msg, e);
        }
    }

    @Override
    public Period getPeriodByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int cDay = calendar.get(Calendar.DAY_OF_MONTH);
        int cMonth = calendar.get(Calendar.MONTH);

        for (Period entry : entries) {

        }

        String msg = "Period for date '" + date + "' not found. Check your configuration.";
        log.error(msg);
        throw new IllegalArgumentException(msg);
    }

    @Override
    public List<Period> getAllPeriods() {
        return entries;
    }

}
