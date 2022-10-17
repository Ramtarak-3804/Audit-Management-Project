package com.mfpe.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mfpe.model.AuthenticationResponse;

import io.swagger.annotations.ApiOperation;

@ControllerAdvice
public class ExceptionHandlerAdvice {
	
	Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);
	
	@ApiOperation(notes = "Handle the MethodArgumentNotValidException during Validation", value = "Send the Invalid Response")
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> validationExceptions(MethodArgumentNotValidException ex) {
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        logger.error(error.getDefaultMessage());
	    });
	    return new ResponseEntity<String>("INVALID Username or Password", HttpStatus.FORBIDDEN);
	}
	
	@ApiOperation(notes = "Handle the Exceptions during Validation", value = "Send the Invalid Response")
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> exception(Exception e) {
		logger.error(e.getMessage());
		AuthenticationResponse authenticationResponse = new AuthenticationResponse(false);
		return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
	}
}
