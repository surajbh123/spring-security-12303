package com.image.demo.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

//@JsonInclude(Include.NON_NULL)
public class ApiException {
	private final String  message;
	private final HttpStatus httpStatus;
	private final Instant instant ;
	private final Class<?> className;
	public ApiException(String message, HttpStatus httpStatus, Instant instant,Class<?> className ) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
		this.instant = instant;
		this.className = className;
	}
	
	
	public String getMessage() {
		return message;
	}
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public Instant getInstant() {
		return instant;
	}

	public Class<?> getClassName() {
		return className;
	}
	
	

}
