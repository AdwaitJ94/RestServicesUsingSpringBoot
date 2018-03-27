package com.example.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
	
	@Autowired
	public EmployeeRepository employeerepository;
	
	public List<Employee> getAllEmployees() {
		
		List<Employee> emp = new ArrayList<Employee>();
		employeerepository.findAll().forEach(emp::add);
		return emp;
	}

	public Employee addEmployee(Employee e) {
		
		if(e == null) {
			return null;
		}else {
			employeerepository.save(e);
			return e;
		}
	}
	
	public Employee getEmployee(String id) {
		return employeerepository.findOne(id);
	}

	public Employee getEmployeeByName(String name) {
		// TODO Auto-generated method stub
		return employeerepository.findByName(name);
	}

	public void updateEmployeeInfo(Employee e, String id) {
		// TODO Auto-generated method stub
		
		Employee emp = employeerepository.findOne(id);
		emp.setId(e.getId());
		emp.setName(e.getName());
		employeerepository.save(e);
	}

	public void employeedelete(String id) {

		employeerepository.delete(id);
		
	}

	public Long getCount() {
		
		return employeerepository.count();
	}
}
