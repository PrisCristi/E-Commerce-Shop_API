package com.ecomerceApi.Priscila;

import com.ecomerceApi.Priscila.exception.UserExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UserExistsException.class})
    public ResponseEntity<String> handleUserExistsException(UserExistsException userExistsException) {
     return new ResponseEntity<>(userExistsException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
