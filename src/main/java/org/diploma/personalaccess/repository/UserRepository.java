package org.diploma.personalaccess.repository;

import org.diploma.personalaccess.entity.Faculty;
import org.diploma.personalaccess.entity.Position;
import org.diploma.personalaccess.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

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

    /**
     * Retrieve users by faculty and positions
     *
     * @param faculty faculty
     * @param positions collection of search positions
     * @return collection of users
     */
    Collection<User> findByFormFacultyAndFormPosition(Faculty faculty, Collection<Position> positions);

}
