package com.example.spring_framework_final_project.kafka.event;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class BookingEvent {
    private Long id;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private Long roomId;
    private Long userId;
}
