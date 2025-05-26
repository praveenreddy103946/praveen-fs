package com.HomiesCart.service;

import com.HomiesCart.entity.Role;
import com.HomiesCart.entity.User;
import com.HomiesCart.entity.UserRole;
import com.HomiesCart.repository.RoleRepository;
import com.HomiesCart.repository.UserRepository;
import com.HomiesCart.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByuserName(username);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getPassword(),
                    getAuthority(user)
            );
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        List<UserRole> userRoles = userRoleRepository.findAllByuserId(user.getUserId());
        List<Role> roles = roleRepository.findAllByRoleIdIn(userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList()));
        roles.forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName().toUpperCase()));
        });
        return authorities;
    }
}

