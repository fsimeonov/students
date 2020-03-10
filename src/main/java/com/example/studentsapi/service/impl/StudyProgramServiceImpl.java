package com.example.studentsapi.service.impl;

import com.example.studentsapi.model.Student;
import com.example.studentsapi.model.StudyProgram;
import com.example.studentsapi.model.exceptions.NotFoundException;
import com.example.studentsapi.model.exceptions.ProgramNotEmptyException;
import com.example.studentsapi.persistence.StudentRepository;
import com.example.studentsapi.persistence.StudyProgramRepository;
import com.example.studentsapi.service.StudyProgramService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StudyProgramServiceImpl implements StudyProgramService {

    private final StudyProgramRepository repository;
    private final StudentRepository studentRepository;

    public StudyProgramServiceImpl(StudyProgramRepository repository, StudentRepository studentRepository) {
        this.repository = repository;
        this.studentRepository = studentRepository;
    }

    @Override
    public StudyProgram createProgram(String name) {
        StudyProgram newProgram = new StudyProgram(name);
        return repository.save(newProgram);
    }

    @Override
    public List<StudyProgram> getAll() {
        return repository.findAll();
    }

    @Override
    public void deleteProgram(Long id) throws NotFoundException, ProgramNotEmptyException {
        StudyProgram program = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Study program with id [%d] not found", id)));
        List<Student> students = studentRepository.findByStudyProgramId(id);
        if(students.size() > 0)
            throw new ProgramNotEmptyException(String.format("Program with id [%d] cannot be deleted because it has students in it", id));
        repository.delete(program);
    }

    @Override
    @Transactional
    public void changeName(Long id, String name) throws NotFoundException {
        StudyProgram program = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Study program with id [%d] not found", id)));
        program.setName(name);
    }

    @Override
    public StudyProgram findByName(Long id) {
        if(repository.getByName(id).isPresent())
            return repository.getByName(id).get();
        return null;
    }
}
