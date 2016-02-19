package org.diploma.personalaccess.repository;

import org.diploma.personalaccess.entity.Position;
import org.diploma.personalaccess.entity.UserIndex;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface UserIndexRepository extends JpaRepository<UserIndex, Long> {

    List<UserIndex> findByPositionAndFillDateBetweenOrderByIndexId(Position position, Date startPeriod, Date endPeriod);

}
