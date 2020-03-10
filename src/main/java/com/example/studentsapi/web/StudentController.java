package com.example.studentsapi.web;

import com.example.studentsapi.model.Student;
import com.example.studentsapi.model.StudentShortInfo;
import com.example.studentsapi.model.StudyProgram;
import com.example.studentsapi.model.exceptions.InvalidIndexException;
import com.example.studentsapi.model.exceptions.MissingArgsException;
import com.example.studentsapi.model.exceptions.NotExistException;
import com.example.studentsapi.model.exceptions.NotFoundException;
import com.example.studentsapi.persistence.StudyProgramRepository;
import com.example.studentsapi.service.StudentService;
import com.example.studentsapi.service.StudyProgramService;
import com.sun.net.httpserver.Headers;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;
    private final StudyProgramService spService;

    public StudentController(StudentService service, StudyProgramService spService) {
        this.service = service;
        this.spService = spService;
    }

    @GetMapping
    public List<StudentShortInfo> getAll() {
        return service.getAll();
    }

    @GetMapping("/{index}")
    public Student getByIndex(@PathVariable String index) throws NotFoundException {
        return service.get(index);
    }

    @GetMapping("/by_study_program/{id}")
    public List<Student> getByProgramID(@PathVariable Long id) {
        return this.service.getByProgram(id);
    }

    @PostMapping
    public ResponseEntity createStudent(@RequestBody Map<String, String> body) throws NotExistException, MissingArgsException, InvalidIndexException {
        StudyProgram sp = spService.findByName(Long.parseLong(body.get("studyProgramName")));
        Student student = service.createStudent(
                body.get("index"),
                body.get("name"),
                body.get("lastName"),
                body.get(sp.getName()));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, String.format("http://localhost:8080/students/%s", student.getIndex()));
        return ResponseEntity.status(201).headers(headers).body(student);
    }


    @PatchMapping("/{index}")
    public ResponseEntity patchStudent(@PathVariable String index, @RequestBody Map<String, String> body) throws NotFoundException, NotExistException {
        service.patchStudent(index, body);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{index}")
    public ResponseEntity deleteStudent(@PathVariable String index) throws NotFoundException {
        service.deleteStudent(index);
        return ResponseEntity.ok().build();
    }
}
