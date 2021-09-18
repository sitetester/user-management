package com.example.usermanagement.service;

public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException(String msg) {
        super(msg);
    }
}
