package com.example.mmandava.microservice6.service;

import com.example.mmandava.microservice6.entity.Employee;
import com.example.mmandava.microservice6.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends Employee {

    @Autowired
    private EmployeeRepository employeeRepository;
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);

    }
}
