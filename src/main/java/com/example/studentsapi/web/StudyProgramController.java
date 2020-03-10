package com.example.studentsapi.web;

import com.example.studentsapi.model.StudyProgram;
import com.example.studentsapi.model.exceptions.NotFoundException;
import com.example.studentsapi.model.exceptions.ProgramNotEmptyException;
import com.example.studentsapi.service.StudyProgramService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/programs")
public class StudyProgramController {
    private final StudyProgramService service;

    public StudyProgramController(StudyProgramService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity createProgram(@RequestBody String name) {
        StudyProgram program = service.createProgram(name);
        return ResponseEntity.ok().body(program);
    }

    @GetMapping("/all")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PatchMapping("/{id}")
    public ResponseEntity changeName(@PathVariable Long id, @RequestBody String name) throws NotFoundException {
        this.service.changeName(id, name);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) throws NotFoundException, ProgramNotEmptyException {
        service.deleteProgram(id);
        return ResponseEntity.ok().build();
    }
}
