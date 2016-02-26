package org.diploma.personalaccess.util;

import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Util methods for parsing json
 *
 * @author Maksim Patapenka
 */
public final class JsonParser {

    /**
     * Logger Log4j
     */
    private static final Logger log = Logger.getLogger(JsonParser.class);

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



    private JsonParser() { }

}
