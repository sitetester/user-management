package com.example.usermanagement.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class LoginData {

    @Email(message = "Please provide a valid email")
    @NotBlank(message = "Please provide an email")
    private String username;

    @NotEmpty(message = "Please provide a password")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
