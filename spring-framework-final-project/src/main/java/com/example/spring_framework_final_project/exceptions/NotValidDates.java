package com.example.spring_framework_final_project.exceptions;

public class NotValidDates extends RuntimeException{
    public NotValidDates(String message) {
        super(message);
    }
}
