package org.diploma.personalaccess.service;

import org.diploma.personalaccess.entity.Index;
import org.diploma.personalaccess.entity.Position;
import org.diploma.personalaccess.entity.UserIndex;

import java.sql.Date;
import java.util.List;

public interface PersonalAccessService {

    List<Index> findIndexesByPosition(Position position);

    void saveOrUpdateIndex(Index index);

    void deleteIndex(Index index);



    List<UserIndex> findUserIndexesByPositionAndPeriod(Position position, Date startPeriod, Date endPeriod);

    void saveUserIndexes(List<UserIndex> userIndexes);

    void setLeadEstimateUserIndexes(List<UserIndex> rawUserIndexes);

}
