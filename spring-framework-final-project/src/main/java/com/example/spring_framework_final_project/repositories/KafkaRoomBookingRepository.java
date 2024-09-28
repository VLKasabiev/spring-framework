package com.example.spring_framework_final_project.repositories;

import com.example.spring_framework_final_project.model.kafka.models.KafkaRoomBooking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface KafkaRoomBookingRepository extends MongoRepository<KafkaRoomBooking, String> {
}
