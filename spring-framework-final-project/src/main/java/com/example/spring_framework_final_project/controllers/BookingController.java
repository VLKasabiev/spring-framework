package com.example.spring_framework_final_project.controllers;

import com.example.spring_framework_final_project.entities.Booking;
import com.example.spring_framework_final_project.kafka.event.BookingEvent;
import com.example.spring_framework_final_project.mapper.booking.BookingMapper;
import com.example.spring_framework_final_project.model.booking.BookingResponse;
import com.example.spring_framework_final_project.model.booking.UpsertBookingRequest;
import com.example.spring_framework_final_project.services.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/booking")
@RequiredArgsConstructor
@Slf4j
public class BookingController {
    private final BookingService bookingService;
    private final BookingMapper bookingMapper;
    private final KafkaTemplate<String, BookingEvent> kafkaBookingTemplate;
    @Value("${app.kafka.kafkaBookingTopic}")
    private String topicName;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<BookingResponse>> findAll() {
        return ResponseEntity.ok(
                bookingService.findAll()
                        .stream().map(bookingMapper::bookingToResponse)
                        .toList()
        );
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<BookingResponse> create(@RequestBody UpsertBookingRequest request,
                                                  @AuthenticationPrincipal UserDetails userDetails) {
        Booking newBooking = bookingService.roomBooking(bookingMapper.requestToBooking(request, userDetails));

        BookingEvent event = bookingMapper.bookingToEvent(newBooking);
        kafkaBookingTemplate.send(topicName, event);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookingMapper.bookingToResponse(newBooking));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookingService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
