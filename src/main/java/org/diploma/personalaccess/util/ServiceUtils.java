package org.diploma.personalaccess.util;

import org.diploma.personalaccess.entity.Index;
import org.diploma.personalaccess.entity.User;
import org.diploma.personalaccess.entity.UserIndex;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public final class ServiceUtils {

    private ServiceUtils() { }

    public static <T> Set<T> subtract(Set<T> minuend, Set<T> subtrahend) {
        Set<T> result = new HashSet<>();
        for (T item : minuend) {
            if (!subtrahend.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }

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
