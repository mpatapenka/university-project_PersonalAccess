package org.diploma.personalaccess.service;

import org.diploma.personalaccess.entity.Index;
import org.diploma.personalaccess.entity.Position;

import java.util.List;

/**
 * Service for working with indexes and depend on it items
 *
 * @author Maksim Patapenka
 */
public interface IndexService {

    /**
     * Save or update index
     *
     * @param index index for saving/updating
     */
    void save(Index index);

    /**
     * Get all indexes
     *
     * @return list of available indexes
     */
    List<Index> getAll();

    /**
     * Get all indexes for specified position
     *
     * @param position Position object
     * @return list of available indexes
     */
    List<Index> getByPosition(Position position);

    /**
     * Find index by 'id'
     *
     * @param id index id (long value)
     * @return found index
     */
    Index getById(long id);

    /**
     * Delete index by 'id'
     *
     * @param id index id (long value)
     */
    void remove(long id);

}
