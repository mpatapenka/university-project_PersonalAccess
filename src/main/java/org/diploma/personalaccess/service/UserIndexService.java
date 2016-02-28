package org.diploma.personalaccess.service;

import org.diploma.personalaccess.bean.Period;
import org.diploma.personalaccess.entity.User;
import org.diploma.personalaccess.entity.UserIndex;

import java.sql.Date;
import java.util.List;

/**
 * Service for working with user indexes and depend on it items
 *
 * @author Maksim Patapenka
 */
public interface UserIndexService {

    /**
     * Get all filled user indexes for period
     *
     * @param user user
     * @param start start date of period
     * @param end end date of period
     * @return list of user indexes
     */
    List<UserIndex> getAllUserIndexesBySpecifiedPeriod(User user, Date start, Date end);

    /**
     * Get all filled user indexes for period
     *
     * @param userId id of user
     * @param start start date of period
     * @param end end date of period
     * @return list of user indexes
     */
    List<UserIndex> getAllUserIndexesBySpecifiedPeriod(long userId, Date start, Date end);

    /**
     * Checking user indexes available for specified period
     *
     * @param user specified user
     * @param period specified period
     * @return true - if user already fill indexes for specified period,
     * false - another one
     */
    boolean isUserIndexesAvailableForPeriod(User user, Period period);

    /**
     * Save all user indexes
     *
     * @param userIndexes user indexes
     */
    void publishAllUserIndexes(List<UserIndex> userIndexes, User user);

}
