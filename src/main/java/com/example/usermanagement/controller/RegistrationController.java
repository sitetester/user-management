package com.example.usermanagement.controller;

import com.example.usermanagement.entity.User;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.service.UploadHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.nio.file.Path;

@Controller
public class RegistrationController {

    @Autowired
    private UploadHelper uploadHelper;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public ModelAndView register(ModelAndView modelAndView, User user) {
        modelAndView
                .addObject("user", user)
                .setViewName("register");

        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView doRegister(ModelAndView modelAndView, @Valid User user, BindingResult bindingResult, @RequestParam("photo") MultipartFile file) {

        Boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();
        if (userExists) {
            bindingResult.reject("email");
            modelAndView
                    .addObject("emailAlreadyTaken", "Email already taken")
                    .setViewName("register");

            return modelAndView;
        }

        if (bindingResult.getErrorCount() > 1) {
            modelAndView.setViewName("register");
            return modelAndView;
        }

        System.out.println(file.getOriginalFilename());

        // proceed with registration
        if (!file.isEmpty()) {
            Path path = uploadHelper.uploadFile(file);
            if (path!= null) {
                user.setPhoto(path.getFileName().toString());
            }
        }

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        userRepository.save(user);

        modelAndView.setViewName("redirect:login");
        return modelAndView;
    }
}
