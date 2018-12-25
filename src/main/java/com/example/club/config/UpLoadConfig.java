package com.example.club.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.servlet.MultipartConfigElement;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan
public class UpLoadConfig {

    @Bean
    public MultipartConfigElement multipartConfigFactory(){
        MultipartConfigFactory factory = new MultipartConfigFactory();

        //单个文件最大
        factory.setMaxFileSize("102400KB");

        factory.setMaxRequestSize("1024000KB");
        return factory.createMultipartConfig();
    }

}
