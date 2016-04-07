package org.diploma.personalaccess.service.impl;

import org.diploma.personalaccess.entity.Index;
import org.diploma.personalaccess.entity.Position;
import org.diploma.personalaccess.repository.IndexRepository;
import org.diploma.personalaccess.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Index service default implementation
 *
 * @author Maksim Patapenka
 */
@Service
public class IndexServiceImpl implements IndexService {

    /**
     * Index repository bean
     */
    @Autowired
    private IndexRepository indexRepository;


    @Override
    @Transactional
    public void save(Index index) {
        indexRepository.save(index);
    }

    @Override
    @Transactional
    public List<Index> getAll() {
        return indexRepository.findAll();
    }

    @Override
    @Transactional
    public List<Index> getByPosition(Position position) {
        return indexRepository.findByAvailablePositions(position);
    }

    @Override
    @Transactional
    public Index getById(long id) {
        return indexRepository.findOne(id);
    }

    @Override
    @Transactional
    public void remove(long id) {
        indexRepository.delete(id);
    }

}
