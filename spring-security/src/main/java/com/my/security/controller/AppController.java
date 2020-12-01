package com.my.security.controller;

import java.time.Instant;

import org.apache.catalina.loader.ResourceEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.security.dto.AuthenticationResponse;
import com.my.security.dto.LoginRequest;
import com.my.security.model.Employee;
import com.my.security.model.UserRole;
import com.my.security.repository.EmployeeRepository;
import com.my.security.service.AuthService;

@RestController
@RequestMapping("/api/test")
public class AppController {

	public AppController() {
		System.out.println("inside app controller");
	}
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private AuthService authService;
	
	@GetMapping
	public ResponseEntity<String> greet(){
		return ResponseEntity.status(HttpStatus.OK).body("Success");
	}
	
	@PostMapping
	public void addAdmin() {
		this.employeeRepository.save(new Employee("suraj",
				this.passwordEncoder.encode("suraj") ,UserRole.ADMIN.toString(),
				Instant.now()));
		
		
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest) {
		System.out.println("inside login controller");
		return new ResponseEntity<AuthenticationResponse>(this.authService.login(loginRequest),HttpStatus.ACCEPTED);
	}
}
