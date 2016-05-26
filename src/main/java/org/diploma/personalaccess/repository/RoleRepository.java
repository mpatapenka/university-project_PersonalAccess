package org.diploma.personalaccess.repository;

import org.diploma.personalaccess.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByCode(String code);

}
