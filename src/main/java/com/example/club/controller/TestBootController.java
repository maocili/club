package com.example.club.controller;

import com.example.club.domain.Account;
import com.example.club.domain.JsonData;
import com.example.club.domain.Student;
import com.example.club.mapper.AccountMapper;
import com.example.club.service.AccountService;
import com.example.club.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/test")
public class TestBootController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountMapper accountMapper;


    @PostMapping("login")
    public JsonData login(String username, String password, HttpServletResponse response) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        try {
            String studentId = accountService.login(account);

            if (studentId == null) {
                return JsonData.buildError("登陆失败");
            } else {
                //查询是否是社团成员
                Student student = accountMapper.getStudent(studentId);
                if (student == null) {
                    return JsonData.buildError("未能查询到该学号，请联系管理员", 1);
                } else {
                    String token = TokenUtils.createJwtToken(account.getStudentId());
                    response.addCookie(new Cookie("token", token));
                    return JsonData.buildSuccess(student, "登陆成功");
                }

            }
        } catch (Exception e) {
            e.getMessage();
            return JsonData.buildError(e.getMessage());
        }

    }


    @PostMapping("student")
    public JsonData student(String studentId) {
        /*
          @ClassName: student
         * 前台输入学号后，post学号进行查询是否录入该学号
         * 如果已经录入自动填充之后的信息
         * 如果学号未被录入返回提示msg提示未被录入
         *
         * studentId: 用于查询该学号的状态
         *
         * code:0 正常执行
         * code:1 尚未被录入
         * code:2 该学号已被注册
         *
         * */
        Student student = new Student();

        student = accountMapper.getStudent(studentId);
        boolean isEntry = accountService.isEntry(studentId);
        if (student == null) {
            return JsonData.buildError("该学号尚未被录入，无法提供注册服务，请联系管理人员", 1);
        } else if (!isEntry) {
            return JsonData.buildError("该学号已被注册", 2);
        } else {
            return JsonData.buildSuccess(student, "如信息有误,请直接修改");
        }

    }

    public int getStatus(String studentId) {
        /*
          @ClassName: getStudentIdStatus
         *
         * 查询学号的状态
         *
         * studentId: 查询该学号
         *
         * code:0 正常执行
         * code:1 尚未被录入
         * code:2 该学号已被注册
         *
         * */
        Student student = new Student();

        student = accountMapper.getStudent(studentId);
        boolean isEntry = accountService.isEntry(studentId);
        if (student == null) {
            return 1;
        } else if (!isEntry) {
            return 2;
        } else {
            return 0;
        }
    }

    @PostMapping("register")
    public JsonData register(Student student) {
        String studentId = student.getStudentId();
        int code = getStatus(studentId);
        switch (code) {
            case 1:
                return JsonData.buildError("该学号尚未被录入，请联系管理员", code);
            case 2:
                return JsonData.buildError("该学号已被注册，请联系管理员", code);
            case 0:
                try {
                    accountMapper.updateStudentById(student);
                    accountService.createAccount(student);
                }catch (Exception e){
                    return JsonData.buildError(e.getMessage());
                }
                return JsonData.buildSuccess("注册成功",code);

            default:
                return JsonData.buildError("未知错误");

        }
    }

}