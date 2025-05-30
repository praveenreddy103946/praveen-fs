package com.HomiesCart.service;

import com.HomiesCart.entity.Role;
import com.HomiesCart.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role createNewRole(Role role) {
            return roleRepository.save(role);
        }

}
