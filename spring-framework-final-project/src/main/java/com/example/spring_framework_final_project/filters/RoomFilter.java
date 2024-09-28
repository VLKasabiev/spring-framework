package com.example.spring_framework_final_project.filters;

import com.example.spring_framework_final_project.validation.RoomFilterValid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RoomFilterValid
public class RoomFilter {
    private Long id;
    private String name;
    private Long maxCost;
    private Long minCost;
    private Long maxOccupancy;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private Long hotelId;
}
