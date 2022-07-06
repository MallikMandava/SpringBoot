package com.example.demo.service;

import com.example.demo.entity.Department;
import com.example.demo.error.DepartmentNotFoundException;
import com.example.demo.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;
    @MockBean
    private DepartmentRepository departmentRepository;
    @BeforeEach
    void setUp() {
        Department department = Department.builder().departmentName("IT").departmentAddress("Bangalore").departmentCode("IT-06").departmentId(1L).build();
        Mockito.when(departmentRepository.findByDepartmentName("IT")).thenReturn(department);
    }

   @Test
   @Disabled
   @DisplayName("Get Data based on valid Department Name")
    public void whenValidDepartmentName_thenDepartmentShouldFound() {
        String departmentName = "IT";
        Department found = departmentService.fetchDepartmentByName(departmentName);
        assertEquals(departmentName,found.getDepartmentName());

    }

    @Test
    @Disabled
    @DisplayName("Throw Error when invalid Department Name")
    public void whenINvalidDepartmentName_thenDepartmentShouldNotFound() throws DepartmentNotFoundException {
        String departmentName = "NoDep";
        Department found = departmentService.fetchDepartmentByName(departmentName);
        if (found!=null)
            assertNotEquals(departmentName,found.getDepartmentName());

    }
}