package com.example.spring_framework_final_project.model.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertBookingRequest {
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private Long roomId;
}
