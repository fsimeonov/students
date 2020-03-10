package com.example.studentsapi.persistence;

import com.example.studentsapi.model.StudentShortInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentShortInfoRepository extends JpaRepository<StudentShortInfo, String> {
}
