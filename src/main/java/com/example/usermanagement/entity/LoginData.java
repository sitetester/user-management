package com.example.usermanagement.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class LoginData {

    @Email(message = "Please provide a valid email")
    @NotBlank(message = "Please provide an email")
    private String email;

    @NotEmpty(message = "Please provide a password")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
