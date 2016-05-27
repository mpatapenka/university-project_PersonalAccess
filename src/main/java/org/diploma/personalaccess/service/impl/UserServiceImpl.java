package org.diploma.personalaccess.service.impl;

import org.apache.log4j.Logger;
import org.diploma.personalaccess.entity.User;
import org.diploma.personalaccess.repository.UserRepository;
import org.diploma.personalaccess.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * UserService internal implementation. Service retrieve user from
 * application database
 *
 * @author Maksim Patapenka
 */
@Service
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {

    /**
     * Logger Log4j
     */
    private static final Logger log = Logger.getLogger(UserServiceImpl.class);


    /**
     * User repository bean
     */
    @Autowired
    private UserRepository userRepository;


    /**
     * Load user from database by username with helps user repository
     *
     * @param username of user
     * @return UserDetails information (Spring Security bean)
     *
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            String msg = "User with username = '" + username + "' was not found!";
            log.warn(msg);
            throw new UsernameNotFoundException(msg);
        }
        return user;
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Collection<User> getSubordinates(User user) {
        return userRepository.findByFormFacultyAndFormPosition(user.getForm().getFaculty(),
                user.getForm().getPosition().getSubs());
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

}
