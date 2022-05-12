package com.springboot.blog.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.springboot.blog.exception.ResourceNotFoundException;

@ControllerAdvice
public class ResourceNotFoundExceptionAdvice {

	//we can have multiple exceptionhandler 
	
	@ResponseBody // the returned value is sent as the response body
	@ExceptionHandler(ResourceNotFoundException.class) // name the exception to be handled
	@ResponseStatus(HttpStatus.NOT_FOUND) // for status to the caller
	public String resourceNotFoundExceptionAdviceHandler(Exception ex) {

		return ex.getMessage();
	}

}
