package com.alpha.td4.service;

import com.alpha.td4.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private List<Student> students = new ArrayList<>();

    public List<Student> addStudents(List<Student> newStudents) {
        students.addAll(newStudents);
        return students;
    }

    public List<Student> getStudents() {
        return students;
    }
}