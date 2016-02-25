package org.diploma.personalaccess.service.impl;

import org.diploma.personalaccess.entity.Index;
import org.diploma.personalaccess.repository.IndexRepository;
import org.diploma.personalaccess.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private IndexRepository indexRepository;

    @Override
    @Transactional
    public void saveOrUpdateIndex(final Index index) {
        Index updated = index.isNew() ? new Index() : indexRepository.findOne(index.getId());
        updated.setEstimate(index.getEstimate());
        updated.setMultiplier(index.getMultiplier());
        updated.setName(index.getName());
        updated.setWorkName(index.getWorkName());
        updated.setAvailablePositions(index.getAvailablePositions());

        indexRepository.save(updated);
    }

    @Override
    @Transactional
    public List<Index> findAllIndexes() {
        return indexRepository.findAll();
    }

}
