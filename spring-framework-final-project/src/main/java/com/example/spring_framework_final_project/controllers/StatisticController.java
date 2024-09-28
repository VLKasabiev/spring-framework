package com.example.spring_framework_final_project.controllers;

import com.example.spring_framework_final_project.model.kafka.models.KafkaUserRegistration;
import com.example.spring_framework_final_project.model.kafka.models.KafkaUserRegistrationModel;
import com.example.spring_framework_final_project.services.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/statistics")
@RequiredArgsConstructor
public class StatisticController {
    private final StatisticsService statisticsService;

    @GetMapping("/download/csv")
    public ResponseEntity<Resource> downloadCsv() {
        StringBuilder csvBuilder = statisticsService.getCsvFile();

        byte[] csvBytes = csvBuilder.toString().getBytes();

        ByteArrayResource resource = new ByteArrayResource(csvBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=data.csv");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(csvBytes.length));

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    @GetMapping()
    public List<KafkaUserRegistrationModel> findAll() {
        return statisticsService.findAll()
                .stream()
                .map(KafkaUserRegistrationModel::from)
                .toList();
    }

    @PostMapping()
    public ResponseEntity<KafkaUserRegistrationModel> create() {
        KafkaUserRegistration registration = new KafkaUserRegistration();
        registration.setId("3");
        registration.setUserId(3L);
//        return statisticsService.save(registration)
//                .map(KafkaUserRegistrationModel::from)
//                .map(ResponseEntity::ok);
        statisticsService.saveUser(registration);

        return ResponseEntity.ok(KafkaUserRegistrationModel.from(registration));
    }
}
