package com.HomiesCart.controllers;


import com.HomiesCart.entity.User;
import com.HomiesCart.repository.UserRepository;
import com.HomiesCart.service.UserService;
import com.HomiesCart.utils.JwtUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping({"/registernewuser"})
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user, JwtUtil.USER_ROLE);
    }

    @PostMapping({"/registernewadmin"})
    public User registerNewAdmin(@RequestBody User user) {
        return userService.registerNewUser(user,JwtUtil.ADMIN_ROLE);
    }

    @GetMapping({"/admin/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/user/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }

    @GetMapping("/getallusers")
    public List<User> getAllUsers(){
       return  userRepository.findAll();
    }
}
