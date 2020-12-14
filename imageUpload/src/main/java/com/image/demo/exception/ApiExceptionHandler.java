package com.image.demo.exception;

import java.time.Instant;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.image.demo.controller.LoggingController;

@ControllerAdvice
public class ApiExceptionHandler {
	
    Logger logger = LogManager.getLogger(ApiExceptionHandler.class);

	@ExceptionHandler(value = {ApiRequestException.class})
	public ResponseEntity<ApiException> handleApiReQuestException( ApiRequestException e ){
		HttpStatus httpStatus = e.getHttpStatus() != null?e.getHttpStatus():HttpStatus.BAD_REQUEST;
		System.out.println(httpStatus);
		this.logger.info("sss");
		ApiException apiException = new ApiException(e.getMessage()
				,httpStatus, Instant.now(),e.getClass());
		return ResponseEntity.status(HttpStatus.OK).body(apiException);
		
	}
	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<ApiException> handleGenericException( Exception e ){
		HttpStatus httpStatus =  HttpStatus.BAD_REQUEST;
		System.out.println(httpStatus);
		this.logger.info("sss");
		ApiException apiException = new ApiException(e.getMessage()
				,httpStatus, Instant.now(),e.getClass());
		return ResponseEntity.status(HttpStatus.OK).body(apiException);
		
	}

}
