package com.HomiesCart.repository;

import com.HomiesCart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByuserName(String userName);

    User findByUserNameIgnoreCase(String userName);
}

