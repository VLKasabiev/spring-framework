package com.example.spring_framework_final_project.model.kafka.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaUserRegistrationModel {
    private String id;
    private Long userId;

    public static KafkaUserRegistrationModel from(KafkaUserRegistration registration) {
        var model = new KafkaUserRegistrationModel();
        model.setId(registration.getId());
        model.setUserId(registration.getUserId());

        return model;
    }

}
