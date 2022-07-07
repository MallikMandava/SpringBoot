package com.mmandava.coder.jwtDemo.controller;

import com.mmandava.coder.jwtDemo.model.jwtRequest;
import com.mmandava.coder.jwtDemo.model.jwtResponse;
import com.mmandava.coder.jwtDemo.service.UserService;
import com.mmandava.coder.jwtDemo.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class jwtController {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private UserService userService ;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String Welcome() {
        return "Welcome to JWT";
    }

    @PostMapping("/authenticate")
    public jwtResponse authenticate(@RequestBody jwtRequest jwtRequest) throws Exception {

        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));
        }
        catch (BadCredentialsException e)
        {
            throw  new Exception("INVALID_CREDITIONALS" , e);
        }

        final UserDetails  userDetails = userService.loadUserByUsername(jwtRequest.getUsername());
        final String token = jwtUtility.generateToken(userDetails);
        return new jwtResponse(token);


    }
}
