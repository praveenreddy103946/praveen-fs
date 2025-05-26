package com.HomiesCart.service;

import com.HomiesCart.dto.JwtRequest;
import com.HomiesCart.dto.JwtResponse;
import com.HomiesCart.entity.Role;
import com.HomiesCart.entity.User;
import com.HomiesCart.entity.UserRole;
import com.HomiesCart.repository.RoleRepository;
import com.HomiesCart.repository.UserRepository;
import com.HomiesCart.repository.UserRoleRepository;
import com.HomiesCart.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class JwtService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String userName = jwtRequest.getUserName();
        String userPassword = jwtRequest.getUserPassword();
         Authentication authentication = null;
        try {
             authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userName, userPassword));
        }catch (Exception e){
            e.printStackTrace();;
        }
        System.out.println("Inside out authenticated");
        if (authentication.isAuthenticated()) {
            System.out.println("Inside authenticated");
            String newGeneratedToken = jwtUtil.generateToken(userName);
            User user = userRepository.findByuserName(userName);
            List<UserRole> userRoles = userRoleRepository.findAllByuserId(user.getUserId());
            List<Role> roles = roleRepository.findAllByRoleIdIn(userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList()));
            user.setRoles(roles);
            return new JwtResponse(user, newGeneratedToken);
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }
}

