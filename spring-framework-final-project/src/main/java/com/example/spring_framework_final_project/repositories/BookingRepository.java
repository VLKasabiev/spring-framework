package com.example.spring_framework_final_project.repositories;

import com.example.spring_framework_final_project.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
