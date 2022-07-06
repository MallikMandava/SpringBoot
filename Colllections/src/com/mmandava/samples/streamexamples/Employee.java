package com.mmandava.samples.streamexamples;

import java.util.List;

public class Employee {
    private int empId;

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", projects=" + projects +
                ", sal=" + sal +
                '}';
    }

    public Employee(int empId, String firstName, String lastName, List<String> projects, double sal) {
        this.empId = empId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.projects = projects;
        this.sal = sal;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String> getProjects() {
        return projects;
    }

    public void setProjects(List<String> projects) {
        this.projects = projects;
    }

    public double getSal() {
        return sal;
    }

    public void setSal(double sal) {
        this.sal = sal;
    }

    private String firstName;
    private String lastName;
    private List<String> projects;
    private double sal;

}
