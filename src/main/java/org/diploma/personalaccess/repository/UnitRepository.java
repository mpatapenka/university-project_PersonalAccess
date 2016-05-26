package org.diploma.personalaccess.repository;

import org.diploma.personalaccess.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {

    Unit findByName(String name);

}
