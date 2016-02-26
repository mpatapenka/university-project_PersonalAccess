package org.diploma.personalaccess.service;

import org.diploma.personalaccess.bean.Period;
import org.diploma.personalaccess.entity.User;
import org.diploma.personalaccess.entity.UserIndex;

import java.util.List;

/**
 * Service for working with user indexes and depend on it items
 *
 * @author Maksim Patapenka
 */
public interface UserIndexService {

    /**
     * Get all filled user indexes for current period of current year
     *
     * @param user user
     * @param period period
     * @return list of user indexes
     */
    List<UserIndex> getAllUserIndexesByCurrentPeriod(User user, Period period);

}
