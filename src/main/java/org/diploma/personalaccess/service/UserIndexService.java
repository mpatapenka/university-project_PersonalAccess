package org.diploma.personalaccess.service;

import org.diploma.personalaccess.bean.Period;
import org.diploma.personalaccess.entity.User;
import org.diploma.personalaccess.entity.UserIndex;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * Service for working with user indexes and depend on it items
 *
 * @author Maksim Patapenka
 */
public interface UserIndexService {

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

    /**
     * Save all self estimates to UserIndex'es
     *
     * @param userIndexes user indexes list with self estimates
     * @param user specified user
     */
    void setUpAllEstimates(List<UserIndex> userIndexes, User user);

    /**
     * Upload additional information about user index
     *
     * @param userIndexId user index ID
     * @param description description of complete work
     * @param doc approve document
     */
    void uploadAdditionalInfo(long userIndexId, String description, MultipartFile doc);

    /**
     * Setup new lead estimates to user index
     *
     * @param userIndexIdMarkDependency map where key - id of user index, value - lead estimate
     * @param user lead of subordinate. Need for validation authorities
     */
    void publishLeadEstimates(Map<Long, Integer> userIndexIdMarkDependency, User user);

    /**
     * Checking that was all estimates were accept by lead for specified
     * user and specified period
     *
     * @param userId specified user id
     * @param start start period date
     * @param end end period date
     * @return true - if user indexes submitted by lead, false - if didn't submit
     */
    boolean isLeadSubmitAllEstimatesForUser(long userId, Date start, Date end);

}
