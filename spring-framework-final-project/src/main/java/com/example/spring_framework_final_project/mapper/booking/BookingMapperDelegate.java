package com.example.spring_framework_final_project.mapper.booking;

import com.example.spring_framework_final_project.entities.Booking;
import com.example.spring_framework_final_project.entities.Room;
import com.example.spring_framework_final_project.entities.User;
import com.example.spring_framework_final_project.exceptions.NotValidDates;

import com.example.spring_framework_final_project.model.booking.UpsertBookingRequest;
import com.example.spring_framework_final_project.services.RoomService;
import com.example.spring_framework_final_project.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;

@Slf4j
public abstract class BookingMapperDelegate implements BookingMapper{
    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;

    @Override
    public Booking requestToBooking(UpsertBookingRequest request, UserDetails userDetails) {
        checkDates(request.getArrivalDate(), request.getDepartureDate());
        User user = userService.findByUsername(userDetails.getUsername());
        Room room = roomService.findById(request.getRoomId());

        Booking booking = new Booking();

        booking.setArrivalDate(request.getArrivalDate());
        booking.setDepartureDate(request.getDepartureDate());
        booking.setUser(user);
        booking.setRoom(room);

        return booking;
    }

    private void checkDates(LocalDate arrivalDate, LocalDate departureDate) {
        if (arrivalDate.isAfter(departureDate)) {
            throw new NotValidDates("Неверно указаны даты прибытия и отбытия! Дата прибытия не может быть позднее даты отбытия!");
        }
    }
}
