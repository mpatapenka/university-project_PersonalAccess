package org.diploma.personalaccess.util;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Util methods for parsing
 *
 * @author Maksim Patapenka
 */
public final class Parser {

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
        } catch (UnsupportedEncodingException ignore) {
            // NOP
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

    private Parser() { }

}
