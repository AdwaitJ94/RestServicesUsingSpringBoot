package com.example.model;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Employee {
	
	@Id
	@NotNull
	private String id;
	
	@NotNull
	@Size(min = 2, message="Name should have at least 2 characters")
	private String name;
	
	@Min(value = 15, message = "Age of employee should be more than 15")
	private int age;
	
	
	public Employee() {
		
	}
	
	public Employee(String name, String id, int age) {
		super();
		this.name = name;
		this.id = id;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "The Employee [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
	
}
