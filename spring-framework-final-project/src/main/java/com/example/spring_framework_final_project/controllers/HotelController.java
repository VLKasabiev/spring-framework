package com.example.spring_framework_final_project.controllers;

import com.example.spring_framework_final_project.entities.Hotel;
import com.example.spring_framework_final_project.filters.HotelFilter;
import com.example.spring_framework_final_project.filters.RoomFilter;
import com.example.spring_framework_final_project.mapper.hotel.HotelMapper;
import com.example.spring_framework_final_project.model.hotel.HotelResponse;
import com.example.spring_framework_final_project.model.hotel.UpsertHotelRequest;
import com.example.spring_framework_final_project.model.hotel.UpsertRatingRequest;
import com.example.spring_framework_final_project.model.room.RoomResponse;
import com.example.spring_framework_final_project.services.HotelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/hotel")
@RequiredArgsConstructor
@Slf4j
public class HotelController {
    private final HotelService hotelService;
    private final HotelMapper hotelMapper;

    @GetMapping("/filter")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<HotelResponse>> filter(@Valid HotelFilter filter) {
        return ResponseEntity.ok(hotelService.filterBy(filter)
                .stream()
                .map(hotelMapper::hotelToResponse)
                .toList());
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<HotelResponse>> findAll() {
        return ResponseEntity.ok(
                hotelService.findAll()
                        .stream()
                        .map(hotelMapper::hotelToResponse)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<HotelResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                hotelMapper.hotelToResponse(hotelService.findById(id))
        );
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<HotelResponse> create(@RequestBody @Valid UpsertHotelRequest request) {
        Hotel newHotel = hotelService.createHotel(hotelMapper.toHotel(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(hotelMapper.hotelToResponse(newHotel));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<HotelResponse> update(@PathVariable Long id, @Valid @RequestBody UpsertHotelRequest request) {
        Hotel updateHotel = hotelService.updateHotel(hotelMapper.toHotel(id, request));
        return ResponseEntity.ok(hotelMapper.hotelToResponse(updateHotel));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void deleteById(@PathVariable Long id) {
        hotelService.deleteHotelById(id);
    }

    @PutMapping("/rating/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<HotelResponse> updateRating(@PathVariable Long id, @RequestBody UpsertRatingRequest request) {
        Hotel updatedHotel = hotelService.updateRating(id, request.getNewMark());

        return ResponseEntity.ok(hotelMapper.hotelToResponse(updatedHotel));
    }
}
