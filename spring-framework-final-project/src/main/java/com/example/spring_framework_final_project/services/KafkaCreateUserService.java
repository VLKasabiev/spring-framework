package com.example.spring_framework_final_project.services;

import com.example.spring_framework_final_project.kafka.event.CreateUserEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaCreateUserService {

    private final List<CreateUserEvent> users = new ArrayList<>();

    public void add(CreateUserEvent event) {
        users.add(event);
    }
}
