package org.diploma.personalaccess.service;

import org.diploma.personalaccess.entity.Index;
import org.diploma.personalaccess.entity.Position;
import org.diploma.personalaccess.repository.IndexRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PersonalAccessServiceImpl implements PersonalAccessService {

    @Resource
    private IndexRepository indexRepository;

    @Override
    @Transactional
    public List<Index> findIndexesByPosition(final Position position) {
        return indexRepository.findIndexesByAvailablePositions(position);
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

}
