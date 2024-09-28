package com.example.spring_framework_final_project.services;

import com.example.spring_framework_final_project.kafka.event.CreateUserEvent;
import com.example.spring_framework_final_project.model.kafka.models.KafkaUserRegistration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaCreateUserService {
    private final KafkaUserRegistrationRepository kafkaUserRegistrationRepository;

    private final List<CreateUserEvent> users = new ArrayList<>();

    public void add(CreateUserEvent event) {
        users.add(event);
    }

    public Mono<KafkaUserRegistration> save(KafkaUserRegistration registration) {
        log.info("Save method is calling!!!");
        log.info("ID: " + registration.getId());
        log.info("User ID: " + registration.getUserId());
        return kafkaUserRegistrationRepository.save(registration);
    }
}
