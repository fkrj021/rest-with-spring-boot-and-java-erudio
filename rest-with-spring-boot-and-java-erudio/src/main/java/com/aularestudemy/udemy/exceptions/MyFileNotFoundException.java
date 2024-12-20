package com.aularestudemy.udemy.exceptions;

import java.io.Serial;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



@ResponseStatus(HttpStatus.NOT_FOUND)
public class MyFileNotFoundException extends RuntimeException{
	
    @Serial
    private static final long serialVersionUID =1L;

    public MyFileNotFoundException(String ex) {
        super(ex);
    }
    
    public MyFileNotFoundException(String ex, Throwable cause) {
        super(ex,cause);
    }
}
