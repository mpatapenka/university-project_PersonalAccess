package org.diploma.personalaccess.repository;

import org.diploma.personalaccess.entity.Index;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Simple realization JPA repository for JavaBean Index
 *
 * @author Maksim Patapenka
 */
@Repository
public interface IndexRepository extends JpaRepository<Index, Long> {
}