package org.diploma.personalaccess.service;

import org.diploma.personalaccess.entity.Index;

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
    void saveOrUpdateIndex(Index index);

    /**
     * Get all indexes
     *
     * @return list of available indexes
     */
    List<Index> findAllIndexes();

    /**
     * Find index by 'id'
     *
     * @param id index id (long value)
     * @return found index
     */
    Index findIndexById(long id);

    /**
     * Delete index by 'id'
     *
     * @param id index id (long value)
     */
    void deleteIndex(long id);

}
