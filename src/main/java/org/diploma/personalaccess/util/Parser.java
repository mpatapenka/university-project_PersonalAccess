package org.diploma.personalaccess.util;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.diploma.personalaccess.bean.Period;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;

/**
 * Util methods for parsing
 *
 * @author Maksim Patapenka
 */
public final class Parser {

    /**
     * Logger Log4j
     */
    private static final Logger log = Logger.getLogger(Parser.class);

    /**
     * Common json converter (from google library)
     */
    private static final Gson GSON = new Gson();

    /**
     * Converter from url encoded string to object
     *
     * @param data url-encoded json string
     * @param clazz class for converting
     * @param <T> supported type
     * @return object by specified type
     */
    public static <T> T convertJsonStringToObject(String data, Class<T> clazz) {
        try {
            data = URLDecoder.decode(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            String msg = "Input data can not converted to target encoding 'UTF-8'.";
            log.debug("Input data: " + data, e);
            log.error(msg, e);
            throw new IllegalArgumentException(msg, e);
        }
        data = data.substring(0, data.length() - 1);
        return GSON.fromJson(data, clazz);
    }

    /**
     * Converter from object to json string
     *
     * @param object object which will be convert
     * @return json string
     */
    public static String convertObjectToJsonString(Object object) {
        return GSON.toJson(object);
    }

    /**
     * Parse period raw string into object
     *
     * @param data raw period string
     * @return period object
     */
    public static Period convertPeriodDataStringToPeriod(String data) {
        final String startEndSeparator = "-";

        String[] dates = data.split(startEndSeparator);

        /* Count of dates, must be two date (start period date and end period date)*/
        if (dates.length != 2) {
            String msg = "Incorrect number of dates in seed data. Current data '" + data + "'.";
            log.debug(msg);
            throw new IllegalArgumentException(msg);
        }

        int[] startDateParams = getDateParametersAsInteger(dates[0]);
        int[] endDateParams = getDateParametersAsInteger(dates[1]);

        Period period = new Period();
        period.setStartDay(startDateParams[0]);
        period.setStartMonth(startDateParams[1]);
        period.setEndDay(endDateParams[0]);
        period.setEndMonth(endDateParams[1]);

        return period;
    }



    /**
     * Parse raw date (which consist of two fields - day and month) into integer values
     *
     * @param rawDate string 'day,month'
     * @return integer array, first value it's day, second it's month
     */
    private static int[] getDateParametersAsInteger(String rawDate) {
        final String separator = ",";

        String[] items = rawDate.split(separator);

        /* Count of items, must be two items because that string have two components day and month */
        final int itemsCount = 2;
        if (items.length != itemsCount) {
            String msg = "Incorrect number of parameters in raw date. Current raw date '" + rawDate + "'.";
            log.debug(msg);
            throw new IllegalArgumentException(msg);
        }

        int[] components = new int[itemsCount];
        try {
            for (int i = 0; i < itemsCount; i++) {
                components[i] = Integer.valueOf(items[i]);
            }
        } catch (NumberFormatException e) {
            String msg = "One of the raw date components is incorrect. Raw date '" + Arrays.toString(items) + "'.";
            log.debug(msg, e);
            throw new IllegalArgumentException(msg, e);
        }

        return components;
    }



    private Parser() { }

}
