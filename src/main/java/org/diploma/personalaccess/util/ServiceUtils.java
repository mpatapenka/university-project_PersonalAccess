package org.diploma.personalaccess.util;

import org.diploma.personalaccess.entity.Index;
import org.diploma.personalaccess.entity.User;
import org.diploma.personalaccess.entity.UserIndex;

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
        userIndex.setLeadEstimate(0);
        userIndex.setSelfEstimate(0);

        return userIndex;
    }

}
