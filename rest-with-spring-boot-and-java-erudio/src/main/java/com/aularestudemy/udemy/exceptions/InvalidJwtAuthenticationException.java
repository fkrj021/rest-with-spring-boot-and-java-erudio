package com.aularestudemy.udemy.exceptions;

import java.io.Serial;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;



@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidJwtAuthenticationException extends AuthenticationException{
    @Serial
    private static final long serialVersionUID =1L;

    public InvalidJwtAuthenticationException(String ex) {
        super(ex);
    }
}
