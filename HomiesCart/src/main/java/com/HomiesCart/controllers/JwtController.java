package com.HomiesCart.controllers;


import com.HomiesCart.dto.JwtRequest;
import com.HomiesCart.dto.JwtResponse;
import com.HomiesCart.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @PostMapping("/generatetoken")
    public JwtResponse createJwtTokeren(@RequestBody JwtRequest jwtRequest) throws Exception{
       return jwtService.createJwtToken(jwtRequest) ;
    }
}
