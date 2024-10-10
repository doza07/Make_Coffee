package com.doza.coffee.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CoffeeExceptionHandler {

    @ExceptionHandler(CoffeeException.class)
    public ResponseEntity<String> handleInsufficientIngredientsException(CoffeeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
