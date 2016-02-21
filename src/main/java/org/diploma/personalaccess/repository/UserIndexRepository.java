package org.diploma.personalaccess.repository;

import org.diploma.personalaccess.entity.Position;
import org.diploma.personalaccess.entity.UserIndex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface UserIndexRepository extends JpaRepository<UserIndex, Long> {

    List<UserIndex> findByUserFormPositionAndFillDateBetweenOrderByIndexId(Position position, Date startPeriod, Date endPeriod);

}
