package com.example.spring_framework_final_project.listener;

import com.example.spring_framework_final_project.kafka.event.CreateUserEvent;
import com.example.spring_framework_final_project.model.kafka.models.KafkaUserRegistration;
import com.example.spring_framework_final_project.services.KafkaCreateUserService;
import com.example.spring_framework_final_project.services.StatisticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaCreateUserListener {
    private final KafkaCreateUserService kafkaCreateUserService;
    private final StatisticsService statisticsService;

    @KafkaListener(topics = "${app.kafka.kafkaUserTopic}",
            groupId = "${app.kafka.kafkaMessageGroupId}",
            containerFactory = "concurrentKafkaUserListenerContainerFactory")
    public void listen(@Payload CreateUserEvent event,
                       @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) UUID key,
                       @Header(value = KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Header(value = KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                       @Header(value = KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp) {
        log.info("Received message: {}", event);
        log.info("Key: {}; Partition: {}; topic: {}; timestamp: {}", key, partition, topic, timestamp);

        kafkaCreateUserService.add(event);
        var registratedUser = new KafkaUserRegistration(UUID.randomUUID().toString(), event.getId());
        KafkaUserRegistration createdUser = statisticsService.saveUser(registratedUser);
        log.info("createdUser ID: " + createdUser.getId());
        log.info("createdUser USER ID: " + createdUser.getUserId());
    }
}
