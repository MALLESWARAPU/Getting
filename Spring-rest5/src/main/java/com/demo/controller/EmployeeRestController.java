package com.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.exceptions.DuplicateEmpIdException;
import com.demo.exceptions.InvalidEmployeeDataException;
import com.demo.exceptions.NoSuchEmployeeException;
import com.demo.model.Department;
import com.demo.model.Employee;
import com.demo.service.EmployeeService;

@RestController
@CrossOrigin
@RequestMapping(path = "rest/employees")
public class EmployeeRestController {
	@Autowired
	private EmployeeService service;
	

	// Reads all employees

	// http://localhost:8089/RestEmployee/rest/employees
	@GetMapping(produces = "application/json")
	public List<Employee> getAllEmployees() {
		List<Employee> result = service.readAllEmployee();
		//int length = result.size();
		return result;
	}

	// http://localhost:8081/RestEmployee/rest/employees/112
	@DeleteMapping(path = "{empId}", produces = "application/json")
	public ResponseEntity<Employee> deleteEmployeeById(@PathVariable("empId") int empId)
			throws NoSuchEmployeeException {
		Employee result = service.readEmployee(empId);
		System.out.println("Employee id is " + result.getEmployeeId());
		ResponseEntity<Employee> response = null;
		if (result.getEmployeeId() > 0) {
			System.out.println("Inside if");
			service.deleteEmployee(empId);
			response = new ResponseEntity<Employee>(result, HttpStatus.OK);
			return response;
		} else {
			System.out.println("Inside else exception");
			throw new NoSuchEmployeeException("Employee with this ID Not Found");
		}
	}

	// http://localhost:8089/RestEmployee/rest/employees
	@PutMapping(path = "{empId}", consumes = "application/json")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
		int result = service.updateEmployee(employee);
		if (result != 0) {
			return ResponseEntity.status(200).build();
		} else {
			return ResponseEntity.status(400).build();
		}

	}

	// @RequestBody annotation binds request body to method parameters. The process
	// of serialization/deserialization is performed by HttpMessageConverter
	// The @ResponseBody annotation tells a controller that the object returned is
	// automatically serialized into JSON and passed back into
	// the HttpResponse object
	// ResponseEntity represents the whole HTTP response: status code, headers, and
	// body. As a result, we can use it to fully configure the HTTP response.

	// http://localhost:8081/RestEmployee/rest/employees
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee)
			throws DuplicateEmpIdException, InvalidEmployeeDataException {
		String errormsg = "";
		System.out.println("add employee called");
		int id = service.createEmployee(employee);
		if (id >0)
			return ResponseEntity.ok(employee);
		throw new DuplicateEmpIdException("id exist");

	}

	// http://localhost:8089/RestEmployee/rest/employees/112
	/*
	 * @GetMapping(path="{empId}",consumes ="application/json") public
	 * ResponseEntity<Employee> getEmployeeById(@PathVariable("empId") int empId)
	 * throws DuplicateEmpIdException, InvalidEmployeeDataException {
	 * 
	 * 
	 * }
	 */

	
	  @ExceptionHandler(value=NoSuchEmployeeException.class)
	  
	  @ResponseStatus(code=HttpStatus.BAD_REQUEST, reason="Employee Not Found")
	  public void handleNoSuchEmployeeException() {
	  
	  }
	 

	
	  @ExceptionHandler(value=DuplicateEmpIdException.class)  
	  @ResponseStatus(code=HttpStatus.CONFLICT,
	  reason="Employee with this ID already Exist") public void
	  handleNoSuchEmployeeException2() {
	  
	  }
	  
	//http://localhost:8089/RestEmployee/rest/employees/getAllDepartments
	  @GetMapping(produces = "application/json",path="/getAllDepartments")
	   public List<Department> getAllDepartments() {
			List<Department> result = service.readAllDepartments();
			//int length = result.size();
			return result;
		}
	  
	  @GetMapping(produces = "application/json",path = "{empId}")
	  public Employee findEmployeeByID(@PathVariable("empId") int empId) {
		  Employee e=service.findEmployeeByID(empId);
		  return e;
	  }
	 

}
