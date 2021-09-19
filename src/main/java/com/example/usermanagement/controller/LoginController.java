package com.example.usermanagement.controller;

import com.example.usermanagement.entity.LoginData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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
                .setViewName("login");

        return modelAndView;
    }

    @PostMapping("/doLogin")
    public ModelAndView doLogin(ModelAndView modelAndView, @Valid LoginData loginData, BindingResult bindingResult) {
        modelAndView.setViewName("login");

        if (bindingResult.hasErrors()) {
            return modelAndView;
        }

        try {
            UserDetails user = userDetailsService.loadUserByUsername(loginData.getEmail());
            if (new BCryptPasswordEncoder().matches(loginData.getPassword(), user.getPassword())) {
                modelAndView.setViewName("redirect:/admin/demo");
            } else {
                modelAndView.addObject("invalidPassword", "Invalid password");
            }

        } catch (UsernameNotFoundException usernameNotFoundException) {
            modelAndView
                    .addObject("invalidEmail", "Email doesn't exist")
                    .setViewName("login");
        }

        return modelAndView;
    }

    @GetMapping("/dashboard")
    public ModelAndView dashboard(ModelAndView modelAndView) {
        return modelAndView;
    }

}
