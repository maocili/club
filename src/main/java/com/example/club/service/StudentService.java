package com.example.club.service;

import com.example.club.domain.Student;

import java.util.List;

public interface StudentService {
    public int addStudent(Student student);
    public List<Student> getAllStudent(int lines,int start);

}
