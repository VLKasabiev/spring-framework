package com.example.spring_framework_final_project.services;

import com.example.spring_framework_final_project.model.kafka.models.KafkaRoomBooking;
import com.example.spring_framework_final_project.model.kafka.models.KafkaUserRegistration;
import com.example.spring_framework_final_project.repositories.KafkaRoomBookingRepository;
import com.example.spring_framework_final_project.repositories.KafkaUserRegistrationsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final KafkaUserRegistrationsRepository kafkaUserRegistrationsRepository;
    private final KafkaRoomBookingRepository kafkaRoomBookingRepository;

    public List<KafkaUserRegistration> findAll() {
        return kafkaUserRegistrationsRepository.findAll();
    }

    public KafkaUserRegistration saveUser(KafkaUserRegistration registration) {
        return kafkaUserRegistrationsRepository.save(registration);
    }

    public KafkaRoomBooking saveBooking(KafkaRoomBooking booking) {
        return kafkaRoomBookingRepository.save(booking);
    }


    public StringBuilder getCsvFile() {
        StringBuilder builder = new StringBuilder();
        List<KafkaRoomBooking> kafkaRoomBookings = kafkaRoomBookingRepository.findAll();
        List<KafkaUserRegistration> kafkaUsers = kafkaUserRegistrationsRepository.findAll();

        builder.append("registered-users ID:\n");
        kafkaUsers.forEach(ku -> builder.append(ku.getUserId() + ",").append("\n"));
        builder.append("\nroom-bookings:\n");
        kafkaRoomBookings.forEach(rb -> builder.append(rb.getBookingId()).append(", ")
                    .append(rb.getArrivalDate()).append(", ")
                    .append(rb.getDepartureDate()).append(",")
                    .append("\n"));

        return builder;
    }
}
