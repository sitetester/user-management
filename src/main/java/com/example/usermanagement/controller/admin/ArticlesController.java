package com.example.usermanagement.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

// Dummy controller to check "/admin/*" routes for ROLE_ADMIN
@Controller
public class ArticlesController {

    @GetMapping("/admin/articles")
    public String index(ModelAndView modelAndView) {
        return "articles/index";
    }
}
