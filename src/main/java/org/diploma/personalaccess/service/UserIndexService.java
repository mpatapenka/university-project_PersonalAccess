package org.diploma.personalaccess.service;

import org.diploma.personalaccess.bean.Period;
import org.diploma.personalaccess.entity.User;
import org.diploma.personalaccess.entity.UserIndex;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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
     * Get all filled user indexes for period. If user indexes has incorrect size
     * (check with avail indexes) then any user indexes will be created. If user doesn't
     * have any user index, but he has available indexes, then user indexes will be
     * created by each available index (it condition is working only for current period)
     *
     * @param user user
     * @param period period
     * @param year year
     * @param currentPeriod current period
     * @param currentYear current year
     * @return list of user indexes
     */
    List<UserIndex> getAllUserIndexesBySpecifiedPeriod(User user, Period period, int year,
                                                       Period currentPeriod, int currentYear);

    /**
     * Get all user indexes by period
     *
     * @param user user object
     * @param period period object
     * @param year year
     * @return list of user indexes
     */
    List<UserIndex> getAllUserIndexesForLeadBySpecifiedPeriod(User user, Period period, int year);

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
     * Download additional info (approve file)
     *
     * @param docId id of document
     * @param response response for write to it
     */
    void downloadAdditionalInfo(long docId, HttpServletResponse response);

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
