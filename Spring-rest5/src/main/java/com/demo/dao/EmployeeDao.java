package com.demo.dao;

import java.util.List;

import com.demo.model.Department;
import com.demo.model.Employee;

public interface EmployeeDao {
	
	public int createEmployee(Employee employee);
		
	public List<Employee> readAllEmployee();
	
	public Employee readEmployee(int employeeId);
	
	public int deleteEmployee(int employeeId);
	
	public int updateEmployee(Employee employee);

	public List<Department> getAllDepartments();

	public Employee findEmployeeByID(int empId);
}
