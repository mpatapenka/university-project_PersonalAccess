package org.diploma.personalaccess.repository;

import org.diploma.personalaccess.entity.Faculty;
import org.diploma.personalaccess.entity.Position;
import org.diploma.personalaccess.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
     * @param faculties List of faculties
     * @param positions collection of search positions
     * @return collection of users
     */
    Collection<User> findByFormFacultyInAndFormPositionIn(Collection<Faculty> faculties, Collection<Position> positions);

    @Query(value = "select u from User u where u.form.faculty is null " +
            "and u.form.position.id = :positionId ")
    Collection<User> findByPositionAndNullFaculty(@Param("positionId") Long positionId);

}
