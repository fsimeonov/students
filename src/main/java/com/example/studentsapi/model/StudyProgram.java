package com.example.studentsapi.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "programs")
public class StudyProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studyProgram")
//    private List<StudentShortInfo> studentList = new ArrayList<>();

    public StudyProgram() {
    }

    public StudyProgram(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<StudentShortInfo> getStudentList() {
//        return studentList;
//    }
//
//    public void setStudentList(List<StudentShortInfo> studentList) {
//        this.studentList = studentList;
//    }
}
