package com.demo.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.demo.model.Department;
import com.demo.model.Employee;
@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public int createEmployee(Employee employee) {
		em.persist(employee);
		return employee.getEmployeeId();
	}

	@Override
	public List<Employee> readAllEmployee() {
		TypedQuery<Employee> emptqury=em.createQuery("select e from Employee e",Employee.class);
		return emptqury.getResultList();
	}

	@Override
	public Employee readEmployee(int employeeId) {
		// TODO Auto-generated method stub
		
		return em.find(Employee.class,employeeId);
	}

	@Override
	public int deleteEmployee(int employeeId) {
		// TODO Auto-generated method stub
		Employee e= em.find(Employee.class,employeeId);
		if(e!=null) {
		em.remove(e);
		}
		return e.getEmployeeId();
	}

	@Override
	public int updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		em.merge(employee);
		return employee.getEmployeeId();
	}

	@Override
	public List<Department> getAllDepartments() {
		// TODO Auto-generated method stub
		TypedQuery<Department> depttqury=em.createQuery("select d from Department d",Department.class);
		return depttqury.getResultList();
	}

	@Override
	public Employee findEmployeeByID(int empId) {
		// TODO Auto-generated method stub
		return em.find(Employee.class,empId);
	}

}
