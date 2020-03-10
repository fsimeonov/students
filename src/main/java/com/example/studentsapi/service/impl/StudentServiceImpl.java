package com.example.studentsapi.service.impl;

import com.example.studentsapi.model.Student;
import com.example.studentsapi.model.StudentShortInfo;
import com.example.studentsapi.model.StudyProgram;
import com.example.studentsapi.model.exceptions.InvalidIndexException;
import com.example.studentsapi.model.exceptions.MissingArgsException;
import com.example.studentsapi.model.exceptions.NotExistException;
import com.example.studentsapi.model.exceptions.NotFoundException;
import com.example.studentsapi.persistence.StudentRepository;
import com.example.studentsapi.persistence.StudentShortInfoRepository;
import com.example.studentsapi.persistence.StudyProgramRepository;
import com.example.studentsapi.service.StudentService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentShortInfoRepository studentShortInfoRepository;
    private final StudyProgramRepository studyProgramRepository;

    public StudentServiceImpl(StudentRepository studentRepository, StudentShortInfoRepository studentShortInfoRepository, StudyProgramRepository studyProgramRepository) {
        this.studentRepository = studentRepository;
        this.studentShortInfoRepository = studentShortInfoRepository;
        this.studyProgramRepository = studyProgramRepository;
    }


    @Override
    public List<StudentShortInfo> getAll() {
        return studentShortInfoRepository.findAll();
    }

    @Override
    public Student get(String index) throws NotFoundException {
        return studentRepository.findById(index)
                .orElseThrow(() -> new NotFoundException(String.format("Student with index [%s] not found.", index)));
    }

    @Override
    public List<Student> getByProgram(Long programId) {
        return studentRepository.findByStudyProgramId(programId);
    }

    @Override
    public Student createStudent(String index, String name, String lastName, String programName) throws NotExistException, MissingArgsException, InvalidIndexException {
        if(index == null || name == null || lastName == null || programName == null)
            throw new MissingArgsException("Missing args");
        if(!index.matches("[0-9]+") || index.length() != 6)
            throw new InvalidIndexException(String.format("Invalid index [%s]", index));
        StudyProgram program = studyProgramRepository.getByName(Long.parseLong(programName))
                .orElseThrow(() -> new NotExistException(String.format("Program with [%s] doesn't exist", programName)));
        Student student = new Student(index, name, lastName, program);
        return studentRepository.save(student);
    }

    @Override
    @Transactional
    public void patchStudent(String index, Map<String, String> attributes) throws NotFoundException, NotExistException {
        Student student = studentRepository.findById(index)
                .orElseThrow(() -> new NotFoundException(String.format("Student with index [%s] not found.", index)));
        if(attributes.containsKey("studyProgramName")) {
            StudyProgram program = studyProgramRepository.getByName(Long.parseLong(attributes.get("studyProgramName")))
                    .orElseThrow(() -> new NotExistException(String.format("Program with [%s] doesn't exist", attributes.get("studyProgramName"))));
        }
        if(attributes.containsKey("name")) student.setName(attributes.get("name"));
        if(attributes.containsKey("lastName")) student.setLastName(attributes.get("lastName"));
    }

    @Override
    public void deleteStudent(String index) throws NotFoundException {
        Student student = studentRepository.findById(index)
                .orElseThrow(() -> new NotFoundException(String.format("Student with index [%s] not found.", index)));
        studentRepository.delete(student);
    }
}
