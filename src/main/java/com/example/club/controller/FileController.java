package com.example.club.controller;

import com.example.club.domain.Activity;
import com.example.club.domain.JsonData;
import com.example.club.service.ActivtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class FileController {

    @Autowired
    ActivtyService activtyService;

    @RequestMapping(value = "/page/upload")
    public Object uppage() {

        return "upload";

    }

    private static final String filePath = "E:\\img\\";

    @RequestMapping(value = "/api/upload")
    @ResponseBody
    public JsonData upload(@RequestParam("img_src") MultipartFile file, HttpServletRequest request) {
        /*
         *处理提交的上传文件
         * 返回类 JsonData
         *
         * */
        //上传时 包含name的字段因此这里调用了HttpServletRequest接口进行处理
        //
        Activity activity = new Activity();

        //活动标题
        activity.setActivity_title(request.getParameter("activity_title"));

        //活动名字
        activity.setActivity_name(request.getParameter("activity_name"));

        //活动详细
        activity.setActivity_content(request.getParameter("activity_content"));

        //活动地址
        activity.setActivity_address(request.getParameter("activity_address"));

        //用户名称
        activity.setUsername(request.getParameter("username"));

        //活动时间
        activity.setTime(request.getParameter("time"));



        //获取file是否为空
        if (!file.isEmpty()) {

            // 获取文件名
            String fileName = file.getOriginalFilename();
            System.out.println("上传文件名：" + fileName);

            // 获取文件的后缀名,比如图片的jpeg,png
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            System.out.println("上传的后缀名" + suffixName);

            // 文件上传后的路径
            fileName = UUID.randomUUID() + suffixName;
            File dest = new File(filePath + fileName);

            try {
                file.transferTo(dest);
                activity.setImg_src("/" + fileName);
                System.out.println("保存文件成功");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        } else {
            activity.setImg_src("/default.jpg");
        }

        return  JsonData.buildSuccess(activtyService.addActivity(activity));

    }

}
