package com.example.spring_framework_final_project.kafka.event;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class CreateUserEvent {
    private Long id;

    private String username;

    private String email;

    private Set<String> roles = new HashSet<>();
}
