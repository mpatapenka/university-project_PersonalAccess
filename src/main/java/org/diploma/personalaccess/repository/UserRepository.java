package org.diploma.personalaccess.repository;

import org.diploma.personalaccess.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Simple realization JPA repository for JavaBean User
 *
 * @author Maksim Patapenka
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Retrieve 'user' from database by his 'username'
     *
     * @param username of user
     * @return user from database
     */
    User findByUsername(String username);

}
