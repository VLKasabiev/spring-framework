package com.example.spring_framework_final_project.services;

import com.example.spring_framework_final_project.entities.Hotel;
import com.example.spring_framework_final_project.entities.Room;
import com.example.spring_framework_final_project.exceptions.EntityNotFoundException;
import com.example.spring_framework_final_project.filters.HotelFilter;
import com.example.spring_framework_final_project.filters.RoomFilter;
import com.example.spring_framework_final_project.repositories.HotelRepository;
import com.example.spring_framework_final_project.specification.HotelSpecification;
import com.example.spring_framework_final_project.specification.RoomSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HotelService {
    private final HotelRepository hotelRepository;

    public List<Hotel> filterBy(HotelFilter filter) {
        return hotelRepository.findAll(HotelSpecification.withFilter(filter));
    }


    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    public Hotel findById(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                        "Отель с ID {0} не найден!", id
                )));
    }

    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }


    public Hotel updateHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public void deleteHotelById(Long id) {
        hotelRepository.deleteById(id);
    }

    public Hotel updateRating(Long hotelId, Long newMark) {
        Hotel hotel = findById(hotelId);
        return calculateTotalRating(hotel, newMark);
    }

    private Hotel calculateTotalRating(Hotel hotel, Long newMark) {

        if (hotel.getNumberOfRatings() == 0) {
            hotel.setRating(newMark.doubleValue());
        } else {
            Double totalRating = hotel.getRating() * hotel.getNumberOfRatings();
            totalRating = totalRating - hotel.getRating() + newMark.doubleValue();
            hotel.setRating(totalRating / (hotel.getNumberOfRatings()));
        }
        hotel.setNumberOfRatings(hotel.getNumberOfRatings() + 1);
        return hotelRepository.save(hotel);
    }
}
