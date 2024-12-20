package com.aularestudemy.udemy.exceptions.handler;

import com.aularestudemy.udemy.exceptions.ExceptionResponse;
import com.aularestudemy.udemy.exceptions.InvalidJwtAuthenticationException;
import com.aularestudemy.udemy.exceptions.RequiredObjectsIsNullException;
import com.aularestudemy.udemy.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllException(
            Exception ex , WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundExceptions(
    		Exception ex , WebRequest request){
    	ExceptionResponse exceptionResponse = new ExceptionResponse(
    			new Date(),
    			ex.getMessage(),
    			request.getDescription(false));
    	return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(RequiredObjectsIsNullException.class)
    public final ResponseEntity<ExceptionResponse> handleBadRequestExceptions(
    		Exception ex , WebRequest request){
    	ExceptionResponse exceptionResponse = new ExceptionResponse(
    			new Date(),
    			ex.getMessage(),
    			request.getDescription(false));
    	return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(InvalidJwtAuthenticationException.class)
    public final ResponseEntity<ExceptionResponse> handleInvalidJwtAuthenticationException(
            Exception ex , WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
    }

}
