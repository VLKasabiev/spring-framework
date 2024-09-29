package com.example.spring_framework_final_project.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Long number;
    private Long cost;
    @Column(name = "max_occupancy")
    private Long maxOccupancy;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude
    List<UnavailableDate> unavailableDates = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private Hotel hotel;

    public void addUnavailableDate(UnavailableDate unavailableDate) {
        unavailableDates.add(unavailableDate);
    }
}
