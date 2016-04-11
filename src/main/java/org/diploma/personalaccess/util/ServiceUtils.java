package org.diploma.personalaccess.util;

import org.apache.log4j.Logger;
import org.diploma.personalaccess.entity.Index;
import org.diploma.personalaccess.entity.User;
import org.diploma.personalaccess.entity.UserIndex;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Util methods for using in services
 *
 * @author Maksim Patapenka
 */
public final class ServiceUtils {

    /**
     * Logger Log4j
     */
    private static final Logger log = Logger.getLogger(ServiceUtils.class);

    /**
     * Can not be instantiated
     */
    private ServiceUtils() { }


    /**
     * Subtract two sets
     *
     * @param minuend minuend set
     * @param subtrahend subtrahend set
     * @param <T> any type (must have correct equals and hashCode)
     * @return subtraction result
     */
    public static <T> Set<T> subtract(Set<T> minuend, Set<T> subtrahend) {
        Set<T> result = new HashSet<>();
        for (T item : minuend) {
            if (!subtrahend.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Create empty user index
     *
     * @param date creation date
     * @param user assigned user
     * @param index referenced index
     * @return empty user index
     */
    public static UserIndex createUserIndexStub(Date date, User user, Index index) {
        UserIndex userIndex = new UserIndex();
        userIndex.setFillDate(date);
        userIndex.setUser(user);
        userIndex.setDocument(null);
        userIndex.setComplete(false);
        userIndex.setDescription(null);
        userIndex.setIndex(index);
        /* This incorrect values need for use like a flag */
        userIndex.setLeadEstimate(-1);
        userIndex.setSelfEstimate(-1);

        return userIndex;
    }

    /**
     * Create value of header 'Content-Disposition'
     *
     * @param filename file name for attach
     * @return content disposition value
     */
    public static String createContentDispositionWithFilename(String filename) {
        final String prefix = "attachment; filename=\"";
        final String suffix = "\"; filename*=UTF-8''";

        String encodedFilename = filename;
        try {
            encodedFilename = URLEncoder.encode(filename.replaceAll(" ", "_"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.warn("Filename can't be converted to UTF-8!", e);
        }

        return prefix + filename + suffix + encodedFilename;
    }

}
