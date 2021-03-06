package com.example.club.service.impl;

import com.example.club.domain.Student;
import com.example.club.mapper.StudentMapper;
import com.example.club.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Override
    public int addStudent(Student student) {
        return studentMapper.addStudent(student);
    }

    @Override
    public List<Student> getStudent(int lines,int start) {
        return studentMapper.getStudent(lines,start);
    }

    @Override
    public List<Student> getAllStudent() {
        return studentMapper.getAllStudent();
    }

    @Override
    public Student getStudentById(String studentId) {
        return studentMapper.getStudentById(studentId);
    }

    @Override
    public int deleteStudentById(String studentId) {
        return studentMapper.deleteStudentById(studentId);
    }
}
