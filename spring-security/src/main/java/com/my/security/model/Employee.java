package com.my.security.model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer empId;
	private String email;
	private String password;
	private String role;
    private Instant createdDate;
	public Employee(String email, String password, String role, Instant createdDate) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
		this.createdDate = createdDate;
	}
    
    

}
