package com.my.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
//@PreAuthorize("hasAnyAuthority('ADMIN')")
public class AdminController {
	
	 
	@GetMapping
	public ResponseEntity<String> greetAdmin(){
		System.out.println("inside admin controller");
		return ResponseEntity.status(HttpStatus.OK).body("This is adminggg");
	}

	
}
