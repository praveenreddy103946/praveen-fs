package com.HomiesCart.repository;

import com.HomiesCart.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long> {

    List<Role> findAllByRoleIdIn(List<Long> collect);

    Role findByroleNameIgnoreCase(String string);
}
