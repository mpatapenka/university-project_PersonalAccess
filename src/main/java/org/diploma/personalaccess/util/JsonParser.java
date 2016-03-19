package org.diploma.personalaccess.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
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
    private static Gson GSON;

    static {
        final GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();

        GSON = builder.create();
    }



    /**
     * Converter from url encoded string to object by class
     *
     * @param data url-encoded json string
     * @param clazz class for converting
     * @param <T> supported type
     * @return object by specified class
     */
    public static <T> T convertJsonStringToObject(String data, Class<T> clazz) {
        data = prepareRawJsonDataString(data);
        return GSON.fromJson(data, clazz);
    }

    /**
     * Converter from url encoded string to object by type
     *
     * @param data url-encoded json string
     * @param type type of convert object
     * @param <T> supported type
     * @return object by specified type
     */
    public static <T> T convertJsonStringToObject(String data, Type type) {
        data = prepareRawJsonDataString(data);
        return GSON.fromJson(data, type);
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
     * Prepare data from request to normal view
     *
     * @param rawData raw json data string
     * @return clear json string
     */
    private static String prepareRawJsonDataString(String rawData) {
        try {
            rawData = URLDecoder.decode(rawData, "UTF-8");
            return rawData.substring(0, rawData.length() - 1);
        } catch (UnsupportedEncodingException e) {
            String msg = "Input rawData can not converted to target encoding 'UTF-8'.";
            log.debug("Input rawData: " + rawData, e);
            log.error(msg, e);
            throw new IllegalArgumentException(msg, e);
        }
    }



    private JsonParser() { }

}
