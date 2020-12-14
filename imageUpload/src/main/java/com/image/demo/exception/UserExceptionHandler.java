package com.image.demo.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {

	
	@ExceptionHandler(value = {UserException.class})
	public ResponseEntity<Object> handleUserException(UserException e ){
		HttpStatus httpStatus = e.getHttpStatus();
		
		ApiException apiException = new ApiException(e.getMessage(), httpStatus,Instant.now() , UserException.class);
		
		return ResponseEntity.status(httpStatus).body(apiException);
		
	}
}
