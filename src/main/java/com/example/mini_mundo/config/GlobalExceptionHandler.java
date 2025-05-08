package com.example.mini_mundo.config;

import com.example.mini_mundo.Exceptions.NaoEcontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NaoEcontradoException.class)
    public ResponseEntity<?> handleNaoEncrontrado(NaoEcontradoException naoEcontradoException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(naoEcontradoException.getMessage());
    }
}
