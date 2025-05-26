package com.HomiesCart.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_roles")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {

    @Id
    @Column(name = "user_role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userRoleId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "role_id")
    private Long roleId;

}
