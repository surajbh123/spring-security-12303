package com.image.demo.exception;

import org.springframework.http.HttpStatus;

public class UserException extends RuntimeException {
	
	private  HttpStatus httpStatus ;
	
	public UserException( String message ,HttpStatus httpStatus ) {
		super(message);
		this.httpStatus = httpStatus;
	}
	
	
	public UserException() {
		super();
	}


	public UserException(String message) {
		super(message);
	}


	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
	

}
