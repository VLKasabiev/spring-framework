package com.example.spring_framework_final_project.exceptions;

public class NotValidUser extends RuntimeException{

    public NotValidUser(String message) {
        super(message);
    }
}
