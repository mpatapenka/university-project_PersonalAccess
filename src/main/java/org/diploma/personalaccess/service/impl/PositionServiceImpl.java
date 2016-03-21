package org.diploma.personalaccess.service.impl;

import org.diploma.personalaccess.entity.Position;
import org.diploma.personalaccess.repository.PositionRepository;
import org.diploma.personalaccess.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Position service default implementation
 *
 * @author Maksim Patapenka
 */
@Service
public class PositionServiceImpl implements PositionService {

    /**
     * Position repository bean
     */
    @Autowired
    private PositionRepository positionRepository;


    @Override
    @Transactional
    public List<Position> findAll() {
        return positionRepository.findAll();
    }

}
