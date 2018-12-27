package com.example.club.controller;

import com.example.club.config.WebSecurityConfig;
import com.example.club.domain.Activity;
import com.example.club.service.ActivtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.WebSecurityEnablerConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class IndexController {


    @GetMapping("/index")
    public String index(@SessionAttribute(WebSecurityConfig.SESSION_KEY) String account, Model model) {
        return "index";
    }

    @GetMapping("/aboutus")
    public String aboutus() {
        return "About_us";
    }

    @GetMapping("/video")
    public String video() {
        return "video";
    }

    @GetMapping("/contactus")
    public String contactus() {
        return "contactus";
    }

    @Autowired
    ActivtyService activtyService;
    @RequestMapping("/activity/page/{id}")
    public String getPage(@PathVariable int id, Model model) {
        System.out.println(id);
        Activity activity = activtyService.getActivityById(id);
        if (activity==null){
            return "error";
        }
        model.addAttribute("activity",activity);
        return "single";
    }

}
