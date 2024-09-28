package com.example.spring_framework_final_project.services;

import com.example.spring_framework_final_project.entities.Booking;
import com.example.spring_framework_final_project.entities.Room;
import com.example.spring_framework_final_project.entities.UnavailableDate;
import com.example.spring_framework_final_project.exceptions.BookingUnavailable;
import com.example.spring_framework_final_project.repositories.BookingRepository;
import com.example.spring_framework_final_project.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingService {
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }
    public Booking roomBooking(Booking booking) {
        log.info("Room unDates size: " + booking.getRoom().getUnavailableDates().size());
        log.info("Booking ID: " + booking.getId());
        log.info("Room ID in roomBookingMethod: " + booking.getRoom().getId());
        if (checkDates(booking.getRoom(), booking.getArrivalDate(), booking.getDepartureDate())) {
            UnavailableDate newDate = new UnavailableDate();
            newDate.setArrivalDate(booking.getArrivalDate());
            newDate.setDepartureDate(booking.getDepartureDate());
            newDate.setRoom(booking.getRoom());
//            Room room = booking.getRoom();
            booking.getRoom().addUnavailableDate(newDate);
            roomRepository.save(booking.getRoom());
            return bookingRepository.save(booking);
        }

        throw new BookingUnavailable("Бронирование в эти даты невозможно!");
    }

    private boolean checkDates(Room room, LocalDate arrivalDate, LocalDate departureDate) {
        for (UnavailableDate date : room.getUnavailableDates()) {
            if ((arrivalDate.isAfter(date.getArrivalDate()) && arrivalDate.isBefore(date.getDepartureDate()))
             || (departureDate.isAfter(date.getArrivalDate()) && departureDate.isBefore(date.getDepartureDate()))) {
                return false;
            }
        }
        return true;
    }

    public void deleteById(Long id) {
        bookingRepository.deleteById(id);
    }
}
