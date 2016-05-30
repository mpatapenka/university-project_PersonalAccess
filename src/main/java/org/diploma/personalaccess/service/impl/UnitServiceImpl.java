package org.diploma.personalaccess.service.impl;

import org.diploma.personalaccess.entity.Unit;
import org.diploma.personalaccess.repository.UnitRepository;
import org.diploma.personalaccess.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnitServiceImpl implements UnitService {

    @Autowired
    private UnitRepository unitRepository;


    @Override
    public void addNew(Unit unit) {
        unitRepository.save(unit);
    }

    @Override
    public Unit getByName(String name) {
        return unitRepository.findByName(name);
    }

}
