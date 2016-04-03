package org.diploma.personalaccess.service;

import org.diploma.personalaccess.bean.Period;
import org.diploma.personalaccess.entity.User;
import org.diploma.personalaccess.entity.UserIndex;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
     * @param user          user
     * @param period        period
     * @param year          year
     * @param currentPeriod current period
     * @param currentYear   current year
     * @return list of user indexes
     */
    List<UserIndex> getAllUserIndexesBySpecifiedPeriod(User user, Period period, int year,
                                                       Period currentPeriod, int currentYear);

    /**
     * Get all user indexes by period
     *
     * @param user   user object
     * @param period period object
     * @param year   year
     * @return list of user indexes
     */
    List<UserIndex> getAllUserIndexesForLeadBySpecifiedPeriod(User user, Period period, int year);

    /**
     * Save all self estimates to UserIndex'es
     *
     * @param userIndexes user indexes list with self estimates
     * @param user        specified user
     */
    void setupSelfEstimatesOfUser(List<UserIndex> userIndexes, User user);

    /**
     * Upload additional information about user index
     *
     * @param userIndexId user index ID
     * @param description description of complete work
     * @param doc         approve document
     */
    void uploadAdditionalInfo(long userIndexId, String description, MultipartFile doc);

    /**
     * Download additional info (approve file)
     *
     * @param docId    id of document
     * @param response response for write to it
     */
    void downloadAdditionalInfo(long docId, HttpServletResponse response);

    /**
     * Setup new lead estimates to user index
     *
     * @param userIndexes list of editable user indexes
     * @param lead        user - lead
     * @param sub         user - subordinate
     */
    void setupLeadEstimatesOfUser(List<UserIndex> userIndexes, User lead, User sub);

}
