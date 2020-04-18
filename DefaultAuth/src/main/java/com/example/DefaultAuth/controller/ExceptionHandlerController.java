package com.example.DefaultAuth.controller;

import com.example.DefaultAuth.exceptions.WrongPasswordException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*to handle the exceptions as HTTP responses*/

@RestControllerAdvice
public class ExceptionHandlerController{

    @ExceptionHandler
    public ResponseEntity<?> wrongPasswordHandler(WrongPasswordException e){
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}