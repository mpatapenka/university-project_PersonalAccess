package org.diploma.personalaccess.repository;

import org.diploma.personalaccess.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Simple realization JPA repository for JavaBean Document
 *
 * @author Maksim Patapenka
 */
@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
}
