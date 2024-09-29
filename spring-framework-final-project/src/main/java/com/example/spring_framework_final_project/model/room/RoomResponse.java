package com.example.spring_framework_final_project.model.room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponse {
    private Long id;
    private String title;
    private String description;
    private Long number;
    private Long cost;
    private Long maxOccupancy;
    private List<String> unavailableDates;
    private Long hotelId;
}
