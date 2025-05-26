package com.HomiesCart.dto;


import com.HomiesCart.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private User user;
    private String jwtToken;

}
