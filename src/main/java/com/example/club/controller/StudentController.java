package com.example.club.controller;

import com.example.club.domain.JsonData;
import com.example.club.domain.Student;
import com.example.club.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    /**
     * 新增社团成员
     * @param student
     * @return
     */
    @PostMapping("addStudent")
    public JsonData addStudent(Student student)
    {
        return JsonData.buildSuccess("",studentService.addStudent(student));
    }

    /**
     * /student/getAllStudent
     * 获取所有的学生信息
     * @return
     *
     */
    @RequestMapping("getAllStudent")
    public JsonData getAllStudent()
    {
        return JsonData.buildSuccess(studentService.getAllStudent());
    }
}
