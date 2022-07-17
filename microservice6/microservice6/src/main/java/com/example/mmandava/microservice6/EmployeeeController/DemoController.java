package com.example.mmandava.microservice6.EmployeeeController;

import com.example.mmandava.microservice6.entity.Employee;
import com.example.mmandava.microservice6.service.EmployeeService;
import com.example.mmandava.microservice6.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @PostMapping("/employees")
    public  Employee saveEmployee(@RequestBody Employee employee)
    {
        return employeeService.saveEmployee(employee);
    }
}
