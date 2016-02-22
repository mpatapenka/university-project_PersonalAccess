package org.diploma.personalaccess.service;

import org.diploma.personalaccess.entity.Faculty;
import org.diploma.personalaccess.entity.Index;
import org.diploma.personalaccess.entity.Position;
import org.diploma.personalaccess.entity.UserIndex;
import org.diploma.personalaccess.repository.FacultyRepository;
import org.diploma.personalaccess.repository.IndexRepository;
import org.diploma.personalaccess.repository.UserIndexRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

@Service
public class PersonalAccessServiceImpl implements PersonalAccessService {

    @Resource
    private IndexRepository indexRepository;

    @Resource
    private UserIndexRepository userIndexRepository;

    @Resource
    private FacultyRepository facultyRepository;



    @Override
    @Transactional
    public List<Index> findIndexesByPosition(final Position position) {
        return indexRepository.findByAvailablePositions(position);
    }

    @Override
    @Transactional
    public void saveOrUpdateIndex(final Index index) {
        Index internalIndex = index.isNew() ? new Index() : indexRepository.findOne(index.getId());

        internalIndex.setEstimate(index.getEstimate());
        internalIndex.setMultiplier(index.getMultiplier());
        internalIndex.setName(index.getName());
        internalIndex.setWorkName(index.getWorkName());
        internalIndex.setAvailablePositions(index.getAvailablePositions());
        internalIndex.setUserIndexes(index.getUserIndexes());

        indexRepository.save(internalIndex);
    }

    @Override
    @Transactional
    public void deleteIndex(final Index index) {
        indexRepository.delete(index);
    }



    @Override
    @Transactional
    public List<UserIndex> findUserIndexesByPositionAndPeriod(final Position position, final Date startPeriod, final Date endPeriod) {
        return userIndexRepository.findByUserFormPositionAndFillDateBetweenOrderByIndexId(position, startPeriod, endPeriod);
    }

    @Override
    @Transactional
    public void saveUserIndexes(final List<UserIndex> userIndexes) {
        for (UserIndex userIndex : userIndexes) {
            userIndexRepository.save(userIndex);
        }
    }

    @Override
    @Transactional
    public void setLeadEstimateUserIndexes(final List<UserIndex> rawUserIndexes) {
        for (UserIndex rawUserIndex : rawUserIndexes) {
            UserIndex userIndex = userIndexRepository.findOne(rawUserIndex.getId());
            userIndex.setLeadEstimate(rawUserIndex.getLeadEstimate());

            userIndexRepository.save(userIndex);
        }
    }



    @Override
    @Transactional
    public void saveFaculty(Faculty faculty) {
        facultyRepository.save(faculty);
    }

    @Override
    @Transactional
    public List<Faculty> findAllFaculties() {
        return facultyRepository.findAll();
    }

}
