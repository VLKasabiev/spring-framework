package com.example.spring_framework_final_project.services;

import com.example.spring_framework_final_project.kafka.event.BookingEvent;
import com.example.spring_framework_final_project.model.kafka.models.KafkaRoomBooking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KafkaBookingService {
    private final List<BookingEvent> bookings = new ArrayList<>();

    public void add(BookingEvent event) {
        bookings.add(event);
    }

}
