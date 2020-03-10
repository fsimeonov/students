package com.example.studentsapi.model.exceptions;

public class MissingArgsException extends Exception {
    public MissingArgsException(String s) {
        super(s);
    }
}