package org.diploma.personalaccess.service;

import org.diploma.personalaccess.entity.Index;
import org.diploma.personalaccess.entity.Position;

import java.util.List;

public interface PersonalAccessService {

    List<Index> findIndexesByPosition(Position position);

    void saveOrUpdateIndex(Index index);

    void deleteIndex(Index index);

}
