package org.diploma.personalaccess.repository;

import org.diploma.personalaccess.entity.Index;
import org.diploma.personalaccess.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Simple realization JPA repository for JavaBean Index
 *
 * @author Maksim Patapenka
 */
@Repository
public interface IndexRepository extends JpaRepository<Index, Long> {

    /**
     * Get all indexes for specified position from database
     *
     * @param position Position object
     * @return list of indexes
     */
    List<Index> findByAvailablePositions(Position position);

    /**
     * Get all indexes for specified position with specified archived flag from database
     *
     * @param position Position object
     * @param isArchived Archived flag
     * @return List of indexes
     */
    Set<Index> findByAvailablePositionsAndIsArchived(Position position, boolean isArchived);

}