package com.image.demo.exception;

import java.time.Instant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {

    Logger logger = LogManager.getLogger(UserExceptionHandler.class);

	
	@ExceptionHandler(value = {UserException.class})
	public ResponseEntity<Object> handleUserException(UserException e ){
		HttpStatus httpStatus = e.getHttpStatus() != null?e.getHttpStatus():HttpStatus.BAD_REQUEST;		
		ApiException apiException = new ApiException(e.getMessage(), httpStatus,Instant.now() , UserException.class);
		this.logger.info("hiii");
		return ResponseEntity.status(httpStatus).body(apiException);
		
	}
}
