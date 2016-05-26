package org.diploma.personalaccess.service;

import org.diploma.personalaccess.entity.Role;

public interface RoleService {

    Role getRoleByCode(String code);


    String ROLE_ADMIN = "ROLE_ADMIN";

    String ROLE_USER = "ROLE_USER";

}
