package com.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "DEPT_tbl1")
public class Department {
	@Id
	@Column(name = "id")
	private int departmentId;
	@Column(name = "name")
	private String departmentName;
	//@OneToMany(mappedBy="department--->this is name of the feild of the Department class
	//@OneToMany
	//private Set<Employee> employees;	//Initialization required to avoid NullPointerException
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	

	
	/*public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	*/


}
