package com.example.spring_framework_final_project.controllers;

import com.example.spring_framework_final_project.entities.Role;
import com.example.spring_framework_final_project.entities.RoleType;
import com.example.spring_framework_final_project.entities.User;
import com.example.spring_framework_final_project.kafka.event.CreateUserEvent;
import com.example.spring_framework_final_project.mapper.user.UserMapper;
import com.example.spring_framework_final_project.model.user.UpsertUserRequest;
import com.example.spring_framework_final_project.model.user.UserResponse;
import com.example.spring_framework_final_project.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final KafkaTemplate<String, CreateUserEvent> kafkaUserTemplate;
    @Value("${app.kafka.kafkaUserTopic}")
    private String topicName;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok(
                userService.findAll()
                        .stream()
                        .map(userMapper::userToResponse)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                userMapper.userToResponse(userService.findById(id))
        );
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UpsertUserRequest request,
                                               @RequestParam RoleType roleType) {
        User newUser = userService.createUser(userMapper.requestToUser(request), Role.from(roleType));

        CreateUserEvent event = userMapper.userToEvent(newUser);
        kafkaUserTemplate.send(topicName, event);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.userToResponse(newUser));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<UserResponse> update(@PathVariable Long id,
                                               @RequestBody @Valid UpsertUserRequest request,
                                               @RequestParam RoleType roleType,
                                               @AuthenticationPrincipal UserDetails userDetails) {
        User updatedUser = userService.updateUser(userMapper.requestToUser(id, request), Role.from(roleType), userDetails);

        return ResponseEntity.ok(userMapper.userToResponse(updatedUser));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Void> delete(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        userService.deleteUserById(id, userDetails);
        return ResponseEntity.noContent().build();
    }
}
