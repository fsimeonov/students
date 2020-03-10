package com.example.studentsapi.persistence;

import com.example.studentsapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    public List<Student> findByStudyProgramId(Long id);
}
