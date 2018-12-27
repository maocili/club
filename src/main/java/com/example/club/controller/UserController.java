package com.example.club.controller;

import com.example.club.domain.Enter;
import com.example.club.domain.JsonData;
import com.example.club.mapper.EnterMapper;
import com.example.club.service.EnterService;
import com.example.club.service.impl.EnterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/activity")
public class UserController {

    @Autowired
    EnterMapper enterMapper;

    @Autowired
    EnterService enterService;


    /**
     * /activity/enter
     * 活动报名
     * @param enter
     * activityId 活动id
     * studentId 学生id
     * @return
     */
    @PostMapping("enter")
    public JsonData enter(Enter enter) {
        try {
            int lines = enterService.enterActivty(enter);
            return JsonData.buildSuccess(lines,"报名成功");
        }catch (Exception e)
        {
            return JsonData.buildError("该活动不存在或学号不为社团成员");
        }

    }

    /**
     *
     * 获得所有报名信息
     * @return
     * activity/getAll
    **/
    @RequestMapping("getAll")
    public List getAll(){
        List<Enter> enters= enterService.getAll();
        return enters;
    }
}
