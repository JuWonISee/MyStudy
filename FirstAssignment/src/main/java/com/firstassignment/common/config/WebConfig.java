package com.firstassignment.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/member/signup").setViewName("signup");
        registry.addViewController("/member/login").setViewName("login");
        registry.addViewController("/editprofile").setViewName("profile/editprofile");
        registry.addViewController("/addexperience").setViewName("profile/addexperience");
        registry.addViewController("/addeducation").setViewName("profile/addeducation");
    }
}
