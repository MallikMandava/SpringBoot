package com.example.mmandava.demo.service;

import com.example.mmandava.demo.VO.Department;
import com.example.mmandava.demo.VO.ResponseTemplateVO;
import com.example.mmandava.demo.entity.User;
import com.example.mmandava.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        ResponseTemplateVO vo = new ResponseTemplateVO();

        System.out.println("The User Id is " + userId);
        User user = userRepository.findByUserId(userId);

        System.out.println("The User is " + user);


        vo.setUser(user);




        Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId() ,Department.class);


        vo.setDepartment(department);

        return vo;
    }



    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }


    @Override
    public List<User> fetchUserList() {
        return userRepository.findAll();
    }

   @Override
    public User fetchUserById(Long userId)  {
        return userRepository.findByUserId(userId);
    }

    @Override
    public User fetchFirstByName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }


}
