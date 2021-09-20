package com.example.usermanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

// Dummy controller to check "/admin/*" route for ROLE_ADMIN
@Controller
public class ArticlesController {

    @GetMapping("/admin/articles")
    @ResponseBody
    public String admin_articles(ModelAndView modelAndView) {
        return "admin articles page";
    }

    @GetMapping("/articles")
    @ResponseBody
    public String articles(ModelAndView modelAndView) {
        return "articles page";
    }
}
