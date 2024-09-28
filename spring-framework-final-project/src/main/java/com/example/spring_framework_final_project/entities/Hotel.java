package com.example.spring_framework_final_project.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hotelname;
    private String title;
    private String city;
    private String address;
    @Column(name = "distance_from_centre")
    private Integer distanceFromCentre;
    private Double rating;
    @Column(name = "number_of_ratings")
    private Long numberOfRatings;
}
