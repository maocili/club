package com.example.club.controller;

import com.example.club.utils.TokenUtils;
import com.example.club.domain.JsonData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
//@RequestMapping(value = "/api")
public class LoginController {

    private Map<String, Object> params = new HashMap<>();

    @GetMapping(value = "/login")
    public String login()
    {
        return "login";
    }

}
