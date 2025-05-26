package com.HomiesCart.service;

import com.HomiesCart.entity.Role;
import com.HomiesCart.entity.User;
import com.HomiesCart.entity.UserRole;
import com.HomiesCart.repository.RoleRepository;
import com.HomiesCart.repository.UserRepository;
import com.HomiesCart.repository.UserRoleRepository;
import com.HomiesCart.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


@Service
public class UserService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public User registerNewUser(User userrequest,String roleRequest) {
        // Check if the user already exists
        if (userRepository.findByUserNameIgnoreCase(userrequest.getUserName()) != null) {
            throw new RuntimeException("User already exists");
        }else {
            User savedUser = saveUser(userrequest);
            addRole(savedUser,roleRequest);
            return savedUser;

        }


    }

    private User saveUser(User userrequest) {
        User user = User.builder()
                .userName(userrequest.getUserName())
                .gmail(userrequest.getGmail())
                .firstName(userrequest.getFirstName())
                .lastName(userrequest.getLastName())
                .moblie(userrequest.getMoblie())
                .password(getEncodedPassword(userrequest.getPassword()))
                .build();
        userRepository.save(user);
        return user;
    }

    private void  addRole(User user, String roleRequest){
        Role role = roleRepository.findByroleNameIgnoreCase(roleRequest);
        if(!ObjectUtils.isEmpty(role)){
         UserRole userRole = UserRole.builder()
                 .roleId(role.getRoleId())
                 .userId(user.getUserId())
                 .build();
         userRoleRepository.save(userRole);
        }else {
            throw new RuntimeException("User Role Not Found");
        }
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
