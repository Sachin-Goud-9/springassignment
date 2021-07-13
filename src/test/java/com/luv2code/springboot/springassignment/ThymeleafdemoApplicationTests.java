package com.luv2code.springboot.springassignment;



import com.luv2code.springboot.springassignment.dao.EmployeeRepository;
import com.luv2code.springboot.springassignment.entity.Employee;
import com.luv2code.springboot.springassignment.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class ThymeleafdemoApplicationTests {



	@Autowired
	private EmployeeService employeeService;

	@MockBean
	private EmployeeRepository repository;

	@Test
	public void getEmployeeTest(){
		when(repository.findAll()).thenReturn(Stream
				.of(new Employee(400,"Hari","Haran","hariharan@zemosolabs.com"),
						new Employee(404,"Akhil","Mudiraj","akhilmudiraj@gmail.com"))
				.collect(Collectors.toList()));
		assertEquals(2, employeeService.getAllEmployees().size());

	}

	@Test
	public void getEmployeeByIdTest(){
		int id = 2;
		when(repository.findById(id)).thenReturn(java.util.Optional.of(
				new Employee(4,"Shashikanth","K","shashikanth@gmail.com")));
		assertEquals(new Employee(4,"Shashikanth","K","shashikanth@gmail.com"), employeeService.getEmployeeById(2));
	}

	@Test
	public void saveEmployeeTest(){
		Employee tempEmployee = new Employee(599,"Ajay","Sagar","ajay@gmail.com");
		employeeService.save(tempEmployee);
		verify(repository, times(1)).save(tempEmployee);

	}

	@Test
	public void deleteEmployeeTest(){
		int id = 7;
		Employee employee = new Employee(9,"Sachin","Goud","sachingoud200@gmail.com");
		when(repository.findById(7)).thenReturn(java.util.Optional.of(
				new Employee(9,"Sachin","Goud","sachingoud200@gmail.com")
		));
		employeeService.deleteEmployee(id);
		verify(repository,times(1)).delete(employee);
	}


}
