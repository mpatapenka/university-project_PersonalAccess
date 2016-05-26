package org.diploma.personalaccess.service.impl;

import org.diploma.personalaccess.entity.Role;
import org.diploma.personalaccess.repository.RoleRepository;
import org.diploma.personalaccess.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public Role getRoleByCode(String code) {
        return roleRepository.findByCode(code);
    }

}
