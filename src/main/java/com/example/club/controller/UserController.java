package com.example.club.controller;

import com.example.club.domain.Enter;
import com.example.club.domain.JsonData;
import com.example.club.mapper.EnterMapper;
import com.example.club.service.EnterService;
import com.example.club.service.impl.EnterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    EnterMapper enterMapper;

    @Autowired
    EnterService enterService;


    @PostMapping("enter")
    public JsonData enter(Enter enter) {
//        return JsonData.buildSuccess(enter);

        if (enterService.enterActivty(enter) != 0) {
            return JsonData.buildSuccess(null,"报名成功");
        }
        else {
            return JsonData.buildError("报名失败");
        }
    }
}
