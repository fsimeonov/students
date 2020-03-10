package com.example.studentsapi.service;

import com.example.studentsapi.model.StudyProgram;
import com.example.studentsapi.model.exceptions.NotFoundException;
import com.example.studentsapi.model.exceptions.ProgramNotEmptyException;

import java.util.List;

public interface StudyProgramService {

    public StudyProgram createProgram(String name);
    public List<StudyProgram> getAll();
    public void deleteProgram(Long id) throws NotFoundException, ProgramNotEmptyException;
    public void changeName(Long id, String name) throws NotFoundException;
    public StudyProgram findByName(Long id);
}
