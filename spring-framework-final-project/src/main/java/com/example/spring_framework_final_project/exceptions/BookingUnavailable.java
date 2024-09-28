package com.example.spring_framework_final_project.exceptions;

public class BookingUnavailable extends RuntimeException{
    public BookingUnavailable(String message) {
        super(message);
    }
}
