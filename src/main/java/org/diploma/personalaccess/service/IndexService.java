package org.diploma.personalaccess.service;

import org.diploma.personalaccess.entity.Index;

import java.util.List;

public interface IndexService {

    void saveOrUpdateIndex(Index index);

    List<Index> findAllIndexes();

    Index findIndexById(long id);

}
