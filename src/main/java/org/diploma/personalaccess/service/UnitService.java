package org.diploma.personalaccess.service;

import org.diploma.personalaccess.entity.Unit;

public interface UnitService {

    void addNew(Unit unit);

    Unit getByName(String name);

}
