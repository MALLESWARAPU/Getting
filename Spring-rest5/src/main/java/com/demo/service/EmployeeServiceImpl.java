package com.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.EmployeeDao;
import com.demo.exceptions.DuplicateEmpIdException;
import com.demo.exceptions.InvalidEmployeeDataException;
import com.demo.exceptions.NoSuchEmployeeException;
import com.demo.model.Department;
import com.demo.model.Employee;


@Service("service")
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeDao dao;

	@Override
	@Transactional
	public int createEmployee(Employee employee) throws InvalidEmployeeDataException, DuplicateEmpIdException {
		// TODO Auto-generated method stub
		int id=0;
		System.out.println("create employee called");
		System.out.println("employeeid "+employee.getEmployeeId());
		Employee emp=dao.readEmployee(employee.getEmployeeId());
		if(emp!=null){
			System.out.println("Duplicate id");
			if(emp.getEmployeeId()==employee.getEmployeeId()) {
				throw new DuplicateEmpIdException("Employee with "+employee.getEmployeeId() +" already Exist");
			}
		}
		if(employee.getEmployeeId()<=0) {
		throw new InvalidEmployeeDataException("Employee ID is Invalid");
		}
		id=dao.createEmployee(employee);
		return id;
		
	}

	@Override
	public List<Employee> readAllEmployee() {
		// TODO Auto-generated method stub
		return dao.readAllEmployee();
	}

	@Override
	public Employee readEmployee(int employeeId) throws NoSuchEmployeeException {
		// TODO Auto-generated method stub
		Employee result = dao.readEmployee(employeeId);
		if(result != null) {
		return result;
		}
		else {
			Employee emptyEmp=new Employee();
			emptyEmp.setEmployeeId(0);
			return emptyEmp;
		}
	}

	@Override
	@Transactional
	public int deleteEmployee(int employeeId)  {
		// TODO Auto-generated method stub
		int empid=dao.deleteEmployee(employeeId);
		return empid;
	}

	@Override
	@Transactional
	public int updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return dao.updateEmployee(employee);
	}

	@Override
	public List<Department> readAllDepartments() {
		// TODO Auto-generated method stub
		return dao.getAllDepartments();
	}

	@Override
	public Employee findEmployeeByID(int empId) {
		// TODO Auto-generated method stub
		return dao.findEmployeeByID(empId);
	}

}
