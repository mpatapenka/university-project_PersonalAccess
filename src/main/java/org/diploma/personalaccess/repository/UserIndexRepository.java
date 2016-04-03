package org.diploma.personalaccess.repository;

import org.diploma.personalaccess.entity.User;
import org.diploma.personalaccess.entity.UserIndex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * Simple realization JPA repository for JavaBean UserIndex
 *
 * @author Maksim Patapenka
 */
@Repository
public interface UserIndexRepository extends JpaRepository<UserIndex, Long> {

    /**
     * Find user indexes by specified time period
     *
     * @param userId      specified user id
     * @param startPeriod start period date
     * @param endPeriod   end period date
     * @return list of user indexes
     */
    List<UserIndex> findByUserIdAndFillDateBetween(long userId, Date startPeriod, Date endPeriod);

    /**
     * Find user indexes by specified position for period
     *
     * @param positionId  position id
     * @param startPeriod start period date
     * @param endPeriod   end period date
     * @return list of user indexes
     */
    List<UserIndex> findByUserFormPositionIdAndFillDateBetween(long positionId, Date startPeriod, Date endPeriod);

    /**
     * Count of user indexes by specified time period
     *
     * @param userId      specified user id
     * @param startPeriod start period date
     * @param endPeriod   end period date
     * @return count of user indexes
     */
    Long countByUserAndFillDateBetween(long userId, Date startPeriod, Date endPeriod);

    /**
     * Count of user indexes by specified user, period and specified lead estimate
     *
     * @param user         specified user
     * @param start        start period date
     * @param end          end period date
     * @param leadEstimate lead estimate for search (if you want find not
     *                     filled user indexes by lead, you have to use value '0' (zero)
     * @return count of user indexes
     */
    Long countByUserAndFillDateBetweenAndLeadEstimate(User user, Date start, Date end, int leadEstimate);

}
