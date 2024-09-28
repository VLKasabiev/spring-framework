package com.example.spring_framework_final_project.listener;

import com.example.spring_framework_final_project.kafka.event.BookingEvent;
import com.example.spring_framework_final_project.kafka.event.CreateUserEvent;
import com.example.spring_framework_final_project.model.kafka.models.KafkaRoomBooking;
import com.example.spring_framework_final_project.model.kafka.models.KafkaUserRegistration;
import com.example.spring_framework_final_project.services.KafkaBookingService;
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
public class KafkaBookingListener {
    private final KafkaBookingService bookingService;
    private final StatisticsService statisticsService;

    @KafkaListener(topics = "${app.kafka.kafkaBookingTopic}",
            groupId = "${app.kafka.kafkaMessageGroupId}",
            containerFactory = "concurrentKafkaBookingListenerContainerFactory")
    public void listen(@Payload BookingEvent event,
                       @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) UUID key,
                       @Header(value = KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Header(value = KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                       @Header(value = KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp) {
        log.info("Received message: {}", event);
        log.info("Key: {}; Partition: {}; topic: {}; timestamp: {}", key, partition, topic, timestamp);

        bookingService.add(event);
        var newRoomBooking = new KafkaRoomBooking(UUID.randomUUID().toString(), event.getId(),
                event.getArrivalDate(), event.getDepartureDate());
        statisticsService.saveBooking(newRoomBooking);
    }
}
