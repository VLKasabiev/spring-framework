package com.example.spring_framework_final_project.model.booking;

import com.example.spring_framework_final_project.entities.Room;
import com.example.spring_framework_final_project.entities.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertBookingRequest {
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private Long roomId;
//    private Long userId;
}
