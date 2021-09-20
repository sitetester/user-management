package com.example.usermanagement.controller;

import com.example.usermanagement.entity.LoginData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView, LoginData loginData) {
        modelAndView
                .addObject("loginData", loginData)
                .setViewName("/login");

        return modelAndView;
    }

    @GetMapping("/")
    public String index(ModelAndView modelAndView) {
        return "index";
    }
}
