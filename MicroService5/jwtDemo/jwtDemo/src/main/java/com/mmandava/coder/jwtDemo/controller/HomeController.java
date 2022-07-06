package com.mmandava.coder.jwtDemo.controller;

import com.mmandava.coder.jwtDemo.mvc.jwtRequest;
import com.mmandava.coder.jwtDemo.mvc.jwtResponse;
import com.mmandava.coder.jwtDemo.utiltiy.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/authenticate")
    public jwtResponse authenticate(@RequestBody  jwtRequest jwtRequest){

        String username = jwtRequest.getUsername();
        String password =
    }




}
