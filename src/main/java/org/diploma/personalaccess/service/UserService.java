package org.diploma.personalaccess.service;

import org.diploma.personalaccess.entity.User;

import java.util.Collection;

/**
 * Service for working with user and depend on it items
 *
 * @author Maksim Patapenka
 */
public interface UserService {

    /**
     * Get user by ID
     *
     * @param id id of user
     * @return user object
     */
    User getUserById(long id);

    /**
     * Get user by username
     *
     * @param username username
     * @return user
     */
    User getUserByUsername(String username);

    /**
     * Get subordinates of specified user
     *
     * @param user specified user
     * @return collection of subordinates
     */
    Collection<User> getSubordinates(User user);

}
