package com.example.studentsapi.service;

import com.example.studentsapi.model.Student;
import com.example.studentsapi.model.StudentShortInfo;
import com.example.studentsapi.model.exceptions.InvalidIndexException;
import com.example.studentsapi.model.exceptions.MissingArgsException;
import com.example.studentsapi.model.exceptions.NotExistException;
import com.example.studentsapi.model.exceptions.NotFoundException;

import java.util.List;
import java.util.Map;

public interface StudentService {

    public List<StudentShortInfo> getAll();
    public Student get(String index) throws NotFoundException;
    public List<Student> getByProgram(Long programId);
    public Student createStudent(String index, String name, String lastName, String programName) throws NotExistException, MissingArgsException, InvalidIndexException;
    public void patchStudent(String index, Map<String, String> attributes) throws NotFoundException, NotExistException;
    public void deleteStudent(String index) throws NotFoundException;
}
