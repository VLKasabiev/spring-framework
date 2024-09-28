package com.example.spring_framework_final_project.repositories;

import com.example.spring_framework_final_project.model.kafka.models.KafkaUserRegistration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KafkaUserRegistrationsRepository extends MongoRepository<KafkaUserRegistration, String> {
}
