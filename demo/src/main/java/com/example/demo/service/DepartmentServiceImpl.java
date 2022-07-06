package com.example.demo.service;

import com.example.demo.entity.Department;
import com.example.demo.error.DepartmentNotFoundException;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);

    }

    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll();
    }

    @Override
    public Department fetchDepartmentByName(String departmentName) {
        return departmentRepository.findByDepartmentName(departmentName);

    }

    @Override
    public Department fetchDepartmentById( Long departmentId) throws DepartmentNotFoundException {
        Optional<Department> department = departmentRepository.findById(departmentId);

        System.out.println("Yhe Department id is " + department);
        if (!department.isPresent())
        {
            throw new DepartmentNotFoundException("Department Not Available");
        }
        else
        {
            return department.get();
        }
    }

    @Override
    public Department fetchDepartmentCode(String departmentCode) throws DepartmentNotFoundException {
        Optional<Department> department = Optional.ofNullable(departmentRepository.findByDepartmentCode(departmentCode));
        if (!department.isPresent())
        {
            throw new DepartmentNotFoundException("Department Not Available with the code specified: " + departmentCode);
        }
        else {
            return department.get();
        }

    }
}
