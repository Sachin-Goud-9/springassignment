package com.luv2code.springboot.springassignment.service;

import com.luv2code.springboot.springassignment.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee createEmployee(Employee employee);

    Employee updateEmployee(int id, Employee employee);

    void deleteEmployee(int id);

    Employee getEmployeeById(int id);

    public void save(Employee employee);
}
