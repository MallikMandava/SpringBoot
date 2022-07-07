package com.mmandava.coder.jwtDemo.service;

import com.mmandava.coder.jwtDemo.VO.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class UserService  implements UserDetailsService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // LOgic to get the User from DB
        String url = "http://USER-SERVICE/users/userinfo/";
        UserInfo user = restTemplate.getForObject(url+username , UserInfo.class);
        return new User(user.getFirstName(),user.getPassword() , new ArrayList<>());
    }
}
