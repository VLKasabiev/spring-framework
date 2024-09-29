package com.example.spring_framework_final_project.model.hotel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelResponse {
    private Long id;
    private String hotelname;
    private String title;
    private String city;
    private String address;
    private Double distanceFromCentre;
    private Double rating;
    private Long numberOfRatings;
}
