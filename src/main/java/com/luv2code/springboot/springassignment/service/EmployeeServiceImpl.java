package com.luv2code.springboot.springassignment.service;

import com.luv2code.springboot.springassignment.dao.EmployeeRepository;
import com.luv2code.springboot.springassignment.entity.Employee;
import com.luv2code.springboot.springassignment.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;




@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;


    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(int id, Employee employeeRequest) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Employee Not Found with"+ id));

        employee.setFirstName(employeeRequest.getFirstName());
        employee.setLastName(employeeRequest.getFirstName());
        employee.setEmail(employeeRequest.getEmail());
        return employeeRepository.save(employee);

    }

    @Override
    public void deleteEmployee(int id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Employee Not Found with"+ id));

        employeeRepository.delete(employee);
    }

    @Override
    public Employee getEmployeeById(int id) {
        Optional<Employee> result = employeeRepository.findById(id);

        if(result.isPresent()){
            return result.get();
        }
        else {
            throw new UserNotFoundException("Employee Not Found with" + id);
        }
    }

    public List<Employee> searchBy(String theFirstName, String theLastName) {

        return employeeRepository.
                findByFirstNameContainsAndLastNameContainsAllIgnoreCase(
                        theFirstName, theLastName);
    }

    @Override
    public void save(Employee theEmployee) {
        employeeRepository.save(theEmployee);
    }
}
