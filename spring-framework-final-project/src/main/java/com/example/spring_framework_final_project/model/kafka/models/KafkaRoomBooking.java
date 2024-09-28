package com.example.spring_framework_final_project.model.kafka.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "room_booking")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaRoomBooking {
    @Id
    private String id;
    private Long bookingId;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
}
