package com.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.Main;
import com.demo.exceptions.DuplicateEmpIdException;
import com.demo.exceptions.InvalidEmployeeDataException;
import com.demo.exceptions.NoSuchEmployeeException;
import com.demo.model.Employee;
import com.demo.service.EmployeeService;

@SpringBootTest
class EmployeeServiceImplTest {
	@Autowired
	private EmployeeService service;
	final Logger logger = LoggerFactory.getLogger(Main.class);
	
	@Test
	void testFindEmployeeByIdShouldReturnEmpObject() throws NoSuchEmployeeException, InvalidEmployeeDataException, DuplicateEmpIdException {
		Employee employee = new Employee(114, "Test2", 900000);//testing employee
		//service.createEmployee(employee);
		
		Employee result = service.readEmployee(114); //read from database
		logger.info("employee name "+result.getEmployeeName());
		assertEquals(employee.getEmployeeName(), result.getEmployeeName());
		assertEquals(employee.getEmployeeId(), result.getEmployeeId());
		assertEquals(employee.getEmployeeSalary(), result.getEmployeeSalary());
	
	}
	
	@Test
	void testDuplicateEmployeeIDShouldThrowDuplicateEmpIdException() throws NoSuchEmployeeException {
		assertThrows(DuplicateEmpIdException.class, ()->{
			Employee employee = new Employee(114, "Test", 65000);
			service.createEmployee(employee);
		});
		
	}
	
	@Test
	void testCreateEmployeeShouldThrowInvalidEmployeeDataEmployeeException() {
		assertThrows(InvalidEmployeeDataException.class, ()->{
			Employee employee = new Employee(-1, "Test", 65000);
			service.createEmployee(employee);
		});
	}
	
	
	
	@Test
	void testFindEmployeeByIdShouldThrowNoSuchEmployeeException() {
		assertThrows(NoSuchEmployeeException.class, ()->{
			service.readEmployee(1000);
		});
	}
	


	
	
	
}
	

