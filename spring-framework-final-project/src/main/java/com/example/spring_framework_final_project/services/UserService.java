package com.example.spring_framework_final_project.services;

import com.example.spring_framework_final_project.entities.Role;
import com.example.spring_framework_final_project.entities.RoleType;
import com.example.spring_framework_final_project.entities.User;
import com.example.spring_framework_final_project.exceptions.EntityNotFoundException;
import com.example.spring_framework_final_project.exceptions.NotValidUser;
import com.example.spring_framework_final_project.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                        "Пользователь с ID {0} не найден!", id)));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                        "Пользователь с именем {0} не найден!", username)));
    }

    public User createUser(User user, Role role) {
        user.setRoles(Collections.singleton(role));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        role.setUser(user);

        return userRepository.save(user);
    }

    public User updateUser(User user, Role role, UserDetails userDetails) {
        String errorMessage = "Текущий пользователь не может обновлять данные о пользователе с ID: {0}, потому-что не является его создателем";
        checkUser(user.getId(), userDetails, errorMessage);

        user.setRoles(Collections.singleton(role));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        role.setUser(user);
        return userRepository.save(user);
    }

    public void deleteUserById(Long id, UserDetails userDetails) {
        String errorMessage = "Текущий пользователь не может удалять данные о пользователе с ID: {0}, потому-что не является его создателем";
        checkUser(id, userDetails, errorMessage);
        userRepository.deleteById(id);
    }

    private void checkUser(Long userId, UserDetails userDetails, String errorMessage) {
        User currentUser = findByUsername(userDetails.getUsername());

        boolean hasUserRole = currentUser.getRoles()
                .stream()
                .anyMatch(role -> RoleType.ROLE_USER.equals(role.getAuthority()));

        if (hasUserRole) {
            if (userId.equals(currentUser.getId())) {
                return;
            }

            throw new NotValidUser(MessageFormat.format(errorMessage, userId));
        }
    }
}
