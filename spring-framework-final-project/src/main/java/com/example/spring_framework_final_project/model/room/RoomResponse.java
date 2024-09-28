package com.example.spring_framework_final_project.model.room;

import com.example.spring_framework_final_project.entities.Hotel;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponse {
    private Long id;
    private String name;
    private String description;
    private Long number;
    private Long cost;
    private Long maxOccupancy;
    private List<String> unavailableDates;
    private Long hotelId;
}
