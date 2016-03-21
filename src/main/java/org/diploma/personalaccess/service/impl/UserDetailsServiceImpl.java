package org.diploma.personalaccess.service.impl;

import org.apache.log4j.Logger;
import org.diploma.personalaccess.entity.User;
import org.diploma.personalaccess.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserDetailsService internal implementation. Service retrieve user from
 * application database
 *
 * @author Maksim Patapenka
 */
@Service
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * Logger Log4j
     */
    private static final Logger log = Logger.getLogger(UserDetailsServiceImpl.class);


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

}
