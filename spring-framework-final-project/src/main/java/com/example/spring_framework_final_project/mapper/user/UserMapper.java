package com.example.spring_framework_final_project.mapper.user;

import com.example.spring_framework_final_project.entities.Role;
import com.example.spring_framework_final_project.entities.User;
import com.example.spring_framework_final_project.kafka.event.CreateUserEvent;
import com.example.spring_framework_final_project.model.user.UpsertUserRequest;
import com.example.spring_framework_final_project.model.user.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User requestToUser(UpsertUserRequest request);

    @Mapping(source = "userId", target = "id")
    User requestToUser(Long userId, UpsertUserRequest request);


    @Mapping(target = "roles", expression = "java(mapRoles(user.getRoles()))")
    UserResponse userToResponse(User user);

    @Mapping(target = "roles", expression = "java(mapRoles(user.getRoles()))")
    CreateUserEvent userToEvent(User user);

    default Set<String> mapRoles(Set<Role> roles) {
        return roles.stream()
                .map(role -> role.getAuthority().name())
                .collect(Collectors.toSet());
    }


}
