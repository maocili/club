package com.example.club.service;

import com.example.club.domain.Student;

import java.util.List;

public interface StudentService {
    public int addStudent(Student student);
    public List<Student> getStudent(int lines,int start);
    public List<Student> getAllStudent();
    public Student getStudentById(String studentId);
    public int deleteStudentById(String studentId);


}
