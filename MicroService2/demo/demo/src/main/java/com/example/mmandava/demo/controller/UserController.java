package com.example.mmandava.demo.controller;

import com.example.mmandava.demo.VO.ResponseTemplateVO;
import com.example.mmandava.demo.entity.User;
import com.example.mmandava.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserServiceImpl userService;



    @PostMapping("/users")
    public User saveUser(@Valid @RequestBody User user) {

        return userService.saveUser(user);
    }


    @GetMapping("/users")
    public List<User> fetchUserList() {
        return userService.fetchUserList();

    }

//   @GetMapping("/users/name/{userId}")
//    public User fetchUserById(@PathVariable("userId")  Long userId) {
//        return userService.fetchUserById(userId);
//    }



   @GetMapping("/{id}")
    public ResponseTemplateVO getUserWithDepartment(@PathVariable("id") Long userId) {
        return userService.getUserWithDepartment(userId);
    }


}
