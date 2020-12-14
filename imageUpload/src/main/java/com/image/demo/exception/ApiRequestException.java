package com.image.demo.exception;

import org.springframework.http.HttpStatus;

public class ApiRequestException extends RuntimeException {
	
	private  HttpStatus httpStatus ;

	
	public ApiRequestException(String message) {
		super(message);
	}
	
	public ApiRequestException(String message,HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}
	
	public ApiRequestException(String message,Throwable throwable) {
		super(message, throwable);
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	

}
