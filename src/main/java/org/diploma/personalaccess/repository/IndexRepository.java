package org.diploma.personalaccess.repository;

import org.diploma.personalaccess.entity.Index;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IndexRepository extends JpaRepository<Index, Long> {

    List<Index> findIndexesByPositionId(Long positionId);

}