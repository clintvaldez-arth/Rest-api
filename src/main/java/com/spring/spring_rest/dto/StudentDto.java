package com.spring.spring_rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class StudentDto {
	private Long id;
	
	@Size(min=2, max=50, message="Must be 2 to 50 chracters.")
	private String firstname;
	
	@Size(min=2, max=50, message="Must be 2 to 50 chracters.")
	private String lastName;
	
	@Email(message="Email is required and should be valid")
	private String email;
	
	public StudentDto(Long id, String firstName, String lastName, String email) {
		this.id = id;
		this.firstname = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
