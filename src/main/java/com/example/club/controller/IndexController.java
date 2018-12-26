package com.example.club.controller;

import com.example.club.config.WebSecurityConfig;
import org.springframework.boot.autoconfigure.security.servlet.WebSecurityEnablerConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        return "aboutus";
    }

    @GetMapping("/video")
    public String video() {
        return "video";
    }

    @GetMapping("/contactus")
    public String contactus() {
        return "contactus";
    }


}
