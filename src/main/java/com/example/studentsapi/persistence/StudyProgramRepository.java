package com.example.studentsapi.persistence;

import com.example.studentsapi.model.StudyProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudyProgramRepository extends JpaRepository<StudyProgram, Long> {
    public Optional<StudyProgram> getByName(Long id);
}
