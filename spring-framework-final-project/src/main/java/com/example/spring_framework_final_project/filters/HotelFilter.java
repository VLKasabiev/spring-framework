package com.example.spring_framework_final_project.filters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelFilter {
    private Long id;
    private String hotelname;
    private String title;
    private String city;
    private String address;
    private Double maxDistanceFromCentre;
    private Double minRating;
    private Long maxNumberOfRatings;
}
