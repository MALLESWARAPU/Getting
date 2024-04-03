package com.demo.service;

import java.util.List;

import com.demo.exceptions.DuplicateEmpIdException;
import com.demo.exceptions.InvalidEmployeeDataException;
import com.demo.exceptions.NoSuchEmployeeException;
import com.demo.model.Department;
import com.demo.model.Employee;



public interface EmployeeService {
	public int createEmployee(Employee employee) throws InvalidEmployeeDataException, DuplicateEmpIdException;
	
	public List<Employee> readAllEmployee();
	
	public Employee readEmployee(int employeeId) throws NoSuchEmployeeException;
	
	public int deleteEmployee(int employeeId) throws NoSuchEmployeeException;
	
	public int updateEmployee(Employee employee);

	public List<Department> readAllDepartments();

	public Employee findEmployeeByID(int empId);
}
