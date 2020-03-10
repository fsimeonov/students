package com.example.studentsapi.web;

import com.example.studentsapi.model.exceptions.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler({NotExistException.class, MissingArgsException.class, InvalidIndexException.class})
    public ResponseEntity BadRequestHandler(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity NotFoundHandler(NotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(ProgramNotEmptyException.class)
    public ResponseEntity ProgramNotEmptyException(ProgramNotEmptyException e) {
        return ResponseEntity.status(409).body(e.getMessage());
    }
}
