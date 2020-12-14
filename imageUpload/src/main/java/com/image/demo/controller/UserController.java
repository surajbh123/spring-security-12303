package com.image.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.image.demo.exception.ApiRequestException;
import com.image.demo.exception.UserException;

@RestController
@RequestMapping("user")
public class UserController {
	
	@GetMapping
	@RequestMapping("ex")
	public ResponseEntity<Object> getAll() {
		//throw new ApiRequestException("heyy this is Exception");
		throw new UserException("User Not Found",HttpStatus.NOT_FOUND );
	//	return  ResponseEntity.status(HttpStatus.OK).body("");
	}

}
