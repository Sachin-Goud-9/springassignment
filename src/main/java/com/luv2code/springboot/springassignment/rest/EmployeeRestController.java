package com.luv2code.springboot.springassignment.rest;

import com.luv2code.springboot.springassignment.entity.Employee;
import com.luv2code.springboot.springassignment.entity.EmployeeDto;
import com.luv2code.springboot.springassignment.exception.UserNotFoundException;
import com.luv2code.springboot.springassignment.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {

    @Autowired
    private ModelMapper modelMapper;

    private EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }


    @GetMapping
    public List<EmployeeDto> getAllEmployees(){
        return employeeService.getAllEmployees().stream().map(employee -> modelMapper.map(employee,EmployeeDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable(name = "id") int id){
        Employee employee = employeeService.getEmployeeById(id);

        // convert entity to DTO
        EmployeeDto employeeResponse = modelMapper.map(employee,EmployeeDto.class);

        return ResponseEntity.ok().body(employeeResponse);
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){

        // convert DTO to entity
        Employee employeeRequest = modelMapper.map(employeeDto,Employee.class);

        Employee employee = employeeService.createEmployee(employeeRequest);

        // convert entity to DTO
        EmployeeDto employeeResponse = modelMapper.map(employee, EmployeeDto.class);

        return new ResponseEntity<EmployeeDto>(employeeResponse, HttpStatus.CREATED);
    }

    // change the request for DTO
    // change the response for DTO
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable int id, @RequestBody EmployeeDto employeeDto){
        // convert DTO to Entity
        Employee employeeRequest = modelMapper.map(employeeDto, Employee.class);

        Employee employee = employeeService.updateEmployee(id,employeeRequest);

        // entity to DTO
        EmployeeDto employeeResponse = modelMapper.map(employee, EmployeeDto.class);

        return ResponseEntity.ok().body(employeeResponse);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable(name = "id") int id){
        Employee tempEmployee = employeeService.getEmployeeById(id);

        if(tempEmployee == null){
            throw new UserNotFoundException("Employee Not Found with id: "+ id);
        }
        employeeService.deleteEmployee(id);
        return "Deleted Employee with ID:"+id;
    }
    @GetMapping("/error")
    public void error(){
        throw new UserNotFoundException("No proper mapping");
    }
}
