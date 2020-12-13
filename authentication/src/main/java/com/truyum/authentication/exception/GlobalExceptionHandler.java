package com.truyum.authentication.exception;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(CredentialsException.class)
	public ResponseEntity<Object> credentialException(CredentialsException exception){
		Map<String, Object> map=new LinkedHashMap<String, Object>();
		map.put("timestamp", new Date());
		map.put("status", HttpStatus.BAD_REQUEST.value());
		map.put("message", "Username and password doesn't match.");		
		return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
	}
}