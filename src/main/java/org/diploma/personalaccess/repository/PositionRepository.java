package org.diploma.personalaccess.repository;

import org.diploma.personalaccess.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Simple realization JPA repository for JavaBean Position
 *
 * @author Maksim Patapenka
 */
@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
}
