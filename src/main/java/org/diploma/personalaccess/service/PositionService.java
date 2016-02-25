package org.diploma.personalaccess.service;

import org.diploma.personalaccess.entity.Position;

import java.util.List;

/**
 * Service for working with positions and depend on it items
 *
 * @author Maksim Patapenka
 */
public interface PositionService {

    /**
     * Get all positions from database
     *
     * @return all positions from database
     */
    List<Position> findAll();

}
