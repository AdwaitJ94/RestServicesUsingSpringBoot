package com.example;

import org.springframework.http.*;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeservice;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//To get the details of all the employees
	@RequestMapping(value = "/Employee", method = RequestMethod.GET, produces = "application/json")
	private ResponseEntity<List<Employee>> Employee(){
		
		logger.info("Employee method");
		logger.debug("Total number of records" + employeeservice.getCount());
		
		List<Employee> emp = employeeservice.getAllEmployees();
		return new ResponseEntity<List<Employee>>(emp, HttpStatus.OK);
		
	}
	
	//To add the details of new employee
	@RequestMapping(value = "/EmployeeSave", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<Employee> EmployeeSave(@Valid @RequestBody Employee e) {
		
		logger.info("EmployeeSave method");
		logger.debug("Employee info which is going to save into db:" + e.getId() + " " + e.getName());
		
		employeeservice.addEmployee(e);
		return new ResponseEntity<Employee>(e, HttpStatus.CREATED);
	}
	
	//To get the details of employee through ID
	@RequestMapping(value = "/EmployeeGetInfo/{id}", method = RequestMethod.GET, produces = "application/json")
	private ResponseEntity<Employee> EmployeeGetInfo(@PathVariable("id") String id) throws Exception {
		
		logger.info("EmployeeGetinfo method");
		logger.debug("Employee info id is" + id);
		
		Employee emp = employeeservice.getEmployee(id);
		
		if(emp == null) {
				logger.error("No employee record found with given property");
				
				throw new ResourceNotFound("No resource found with given property");
		}else {
			logger.debug("Employee info which is going to save into db:" + emp.getId() + " " + emp.getName());
			
			return new ResponseEntity<Employee>(emp, HttpStatus.OK);
		}
	}
	
	//To update the details of employee
	@RequestMapping(value = "/EmployeeUpdateInfo/{id}", method = RequestMethod.PUT, produces = "application/json")
	private void EmployeeUpdateInfo(@Valid @RequestBody Employee e, @PathVariable("id") String id) {
		
		logger.info("EmployeeUpdateInfo method");
		logger.debug("Employee info id is" + id);
		
		employeeservice.updateEmployeeInfo(e, id);
	}
	
	//To get the details of employee through name
	@RequestMapping(value = "/EmployeeGetInforByName/{name}", method = RequestMethod.GET, produces = "application/json")
	private ResponseEntity<Employee> EmployeeGetInfoByName(@PathVariable("name") String name) throws Exception {
	
		logger.info("EmployeeGetInfoByName method");
		logger.debug("Employee info name is" + name);
		
		Employee emp = employeeservice.getEmployeeByName(name);
		if(emp == null) {
				logger.error("No employee record found with given property");
				
				throw new ResourceNotFound("No resource found with given property");
		}else {
			logger.debug("Employee info which is going to save into db:" + emp.getId() + " " + emp.getName());
			
			return new ResponseEntity<Employee>(emp, HttpStatus.OK);
		}
	}
	
	//To delete the employee details using ID
	@RequestMapping(value = "/EmployeeDeleteById/{id}", method = RequestMethod.DELETE, produces = "application/json")
	private ResponseEntity<Employee> EmployeeDeleteById(@PathVariable("id") String id) throws Exception {
		
		logger.info("EmployeeDeleteById method");
		logger.debug("Employee info id is" + id);
		
		Employee emp = employeeservice.getEmployee(id);
		
		if(emp == null) {
				logger.error("No employee record found with given property");
				
				throw new ResourceNotFound("No resource found with given property");
		}else {
			logger.debug("Employee info which is going to save into db:" + emp.getId() + " " + emp.getName());
			
			employeeservice.employeedelete(id);
			
			return new ResponseEntity<Employee>(emp, HttpStatus.OK);
		}
	}
}
