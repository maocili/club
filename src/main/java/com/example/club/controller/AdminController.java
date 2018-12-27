package com.example.club.controller;

import com.example.club.config.WebSecurityConfig;
import com.example.club.domain.Account;
import com.example.club.domain.JsonData;
import com.example.club.domain.Permission;
import com.example.club.domain.Student;
import com.example.club.mapper.AccountMapper;
import com.example.club.mapper.AdminMapper;
import com.example.club.service.AccountService;
import com.example.club.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminMapper adminMapper;


    @GetMapping("")
    public String adminPage() {
        return "admin/login";
    }

    @RequestMapping("Home")
    public String homePage() {
        return "admin/Home";
    }

    @RequestMapping("Student")
    public String StudentPage() {
        return "admin/Student";
    }

    @RequestMapping("activity")
    public String activity() {
        return "admin/activity";
    }

    @RequestMapping("upload")
    public String upload() {
        return "upload";
    }


}
