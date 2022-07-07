package com.example.mmandava.demo.service;

import com.example.mmandava.demo.VO.ResponseTemplateVO;
import com.example.mmandava.demo.entity.User;

import java.util.List;

public interface UserService {

    public ResponseTemplateVO getUserWithDepartment(Long userId) ;
    public User saveUser(User user);
    public List<User> fetchUserList();
    public User fetchUserById(Long userId);

    public User fetchFirstByName(String firstName);
}
