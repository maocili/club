package com.example.club.controller;

import com.example.club.domain.JsonData;
import com.example.club.domain.Student;
import com.example.club.mapper.AccountMapper;
import com.example.club.service.AccountService;
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
     * /student/addStudent
     * 新增社团成员
     *
     * @param student
     * @return
     */
    @PostMapping("addStudent")
    public JsonData addStudent(Student student) {
        return JsonData.buildSuccess(student);
//        int lines = studentService.addStudent(student);
//        return JsonData.buildSuccess(lines, "成功添加" + lines + "名社员");
    }

    /**
     * /student/getStudent
     * 获取分页学生信息
     *
     * @return
     */
    @PostMapping("getStudent")
    public JsonData getStudent(int lines, int start) {
        return JsonData.buildSuccess(studentService.getStudent(lines, start));
    }

    /**
     * 获取所有学生
     * @return
     */
    @RequestMapping("getAllStudent")
    public JsonData getAllStudent() {
        return JsonData.buildSuccess(studentService.getAllStudent());

    }

    /**
     * 根据学号获取信息
     * @param studentId
     * @return
     */
    @PostMapping("getStudentById")
    public JsonData getStudent(String studentId) {
        return JsonData.buildSuccess(studentService.getStudentById(studentId));
    }

    /**
     * 删除学生
     * @param studentId
     * @return
     */
    @PostMapping("deleteStudentById")
    public JsonData deleteStudentById(String studentId) {
        return JsonData.buildSuccess(studentService.deleteStudentById(studentId), "成功删除");

    }

    @Autowired
    AccountMapper accountMapper;
    @Autowired
    AccountService accountService;

    /**
     * 更新学生信息
     * @param student
     * @return
     */
    @PostMapping("updateStudent")
    public JsonData updateStudent(Student student) {

        try {
            accountMapper.updateStudentById(student);
        } catch (Exception e) {
            return JsonData.buildError(e.getMessage());
        }
        return JsonData.buildSuccess("更新成功");
    }

}
