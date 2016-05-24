package org.diploma.personalaccess.service.impl;

import org.apache.log4j.Logger;
import org.diploma.personalaccess.entity.User;
import org.diploma.personalaccess.service.UserService;
import org.diploma.personalaccess.util.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CustomLdapAuthoritiesPopulator implements LdapAuthoritiesPopulator {

    /**
     * Logger Log4j
     */
    private static final Logger log = Logger.getLogger(CustomLdapAuthoritiesPopulator.class);


    @Autowired
    private UserService userService;


    @Override
    public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations dirContextOperations,
                                                                        String username) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            user = ServiceUtils.buildUserFromLdapAttributes(dirContextOperations.getAttributes());
            userService.addUser(user);

            user = userService.getUserByUsername(username);

            log.warn("User was created in database with username = '" + user.getUsername() + "'");
        }
        return user.getAuthorities();
    }

}
