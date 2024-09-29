package com.example.spring_framework_final_project.controllers;

import com.example.spring_framework_final_project.services.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/statistics")
@RequiredArgsConstructor
public class StatisticController {
    private final StatisticsService statisticsService;

    @GetMapping("/download/csv")
    @PreAuthorize("hasAnyRole('ADMIN')")
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
}
