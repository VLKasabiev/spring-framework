package com.example.spring_framework_final_project.model.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertUserRequest {
    @NotBlank(message = "Имя пользователя должно быть указано!")
    private String username;
    @NotBlank(message = "Пароль должен быть указан!")
    @Size(min = 5, message = "Пароль должен содержать минимум 5 символов!")
    private String password;
    @NotBlank(message = "Email должен быть указан!")
    private String email;
}
