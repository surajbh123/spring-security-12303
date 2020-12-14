package com.image.demo.exception;

import java.time.Instant;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(value = {ApiRequestException.class})
	public ResponseEntity<ApiException> handleApiReQuestException( ApiRequestException e ){
		HttpStatus httpStatus = e.getHttpStatus() != null?e.getHttpStatus():HttpStatus.BAD_REQUEST;
		//httpStatus = httpStatus != null ? httpStatus.BAD_REQUEST:e.get
		ApiException apiException = new ApiException(e.getMessage()
				,httpStatus, Instant.now(),e.getClass());
		return ResponseEntity.status(httpStatus).body(apiException);
		
	}

}
