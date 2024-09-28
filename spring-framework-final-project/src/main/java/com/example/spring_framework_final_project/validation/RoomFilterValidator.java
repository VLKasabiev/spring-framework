package com.example.spring_framework_final_project.validation;

import com.example.spring_framework_final_project.filters.RoomFilter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RoomFilterValidator implements ConstraintValidator<RoomFilterValid, RoomFilter> {
    @Override
    public boolean isValid(RoomFilter value, ConstraintValidatorContext context) {
        if ((value.getArrivalDate() == null && value.getDepartureDate() != null) ||
                (value.getArrivalDate() != null && value.getDepartureDate() == null)) {
            return false;
        }

        return true;
    }
}
