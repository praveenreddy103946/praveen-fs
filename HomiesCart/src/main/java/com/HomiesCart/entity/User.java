package com.HomiesCart.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(name = "is_active", insertable = false)
    private String isActive;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gmail")
    private String gmail;

    @Column(name = "moblie")
    private String moblie;

    @Transient
    List<Role> roles;

}

