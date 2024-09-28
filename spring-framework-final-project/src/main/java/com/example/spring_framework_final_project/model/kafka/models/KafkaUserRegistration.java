package com.example.spring_framework_final_project.model.kafka.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user_registrations")
public class KafkaUserRegistration {
    @Id
    private String id;

    private Long userId;
}
