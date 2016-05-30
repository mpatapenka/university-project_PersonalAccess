package org.diploma.personalaccess.service.impl;

import org.apache.log4j.Logger;
import org.diploma.personalaccess.entity.Faculty;
import org.diploma.personalaccess.entity.Unit;
import org.diploma.personalaccess.entity.User;
import org.diploma.personalaccess.service.*;
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

    @Autowired
    private PositionService positionService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private UnitService unitService;


    @Override
    public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations dirContextOperations,
                                                                        String username) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            user = ServiceUtils.buildUserFromLdapAttributes(dirContextOperations.getAttributes(),
                    positionService.getAll());

            /* Lol I hardcoded it again :) */
            if ("Проректор".equals(user.getForm().getPosition().getName())) {
                user.setRole(roleService.getRoleByCode(RoleService.ROLE_ADMIN));
            } else {
                user.setRole(roleService.getRoleByCode(RoleService.ROLE_USER));
            }

            Unit oldUnit = unitService.getByName(user.getForm().getUnit().getName());
            if (oldUnit == null) {
                oldUnit = user.getForm().getUnit();
                unitService.addNew(oldUnit);

                oldUnit = unitService.getByName(oldUnit.getName());
            }
            user.getForm().setUnit(oldUnit);

            Faculty faculty = ServiceUtils.associateFacultyByUnit(user.getForm().getUnit(), facultyService.getAll());
            user.getForm().setFaculty(faculty);

            userService.addUser(user);

            user = userService.getUserByUsername(username);

            log.warn("User was created in database with username = '" + user.getUsername() + "'");
        }
        return user.getAuthorities();
    }

}
