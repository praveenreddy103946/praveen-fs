package com.HomiesCart.repository;

import com.HomiesCart.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole,Long> {

    List<UserRole> findAllByuserId(Long userId);
}
