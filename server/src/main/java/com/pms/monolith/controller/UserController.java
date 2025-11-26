package com.pms.monolith.controller;

import com.pms.monolith.dto.LoginRequestDTO;
import com.pms.monolith.dto.UserRequestDTO;
import com.pms.monolith.dto.UserResponseDTO;
import com.pms.monolith.entity.User;
import com.pms.monolith.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserResponseDTO register(@Valid @RequestBody UserRequestDTO requestDTO) {

        User user = new User();
        user.setName(requestDTO.getName());
        user.setEmail(requestDTO.getEmail());
        user.setPassword(requestDTO.getPassword());

        User saved = userService.registerUser(user);

        return UserResponseDTO.builder()
                .id(saved.getId())
                .name(saved.getName())
                .email(saved.getEmail())
                .createdAt(saved.getCreatedAt())
                .build();
    }

    @PostMapping("/login")
    public UserResponseDTO login(@Valid @RequestBody LoginRequestDTO loginDTO) {

        User user = userService.login(loginDTO.getEmail(), loginDTO.getPassword());

        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUser(@PathVariable UUID id) {

        User user = userService.getUserById(id);

        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();
    }

    @PutMapping("/{id}")
    public UserResponseDTO updateUser(
            @PathVariable UUID id,
            @RequestParam String name
    ) {

        User updated = userService.updateUser(id, name);

        return UserResponseDTO.builder()
                .id(updated.getId())
                .name(updated.getName())
                .email(updated.getEmail())
                .createdAt(updated.getCreatedAt())
                .build();
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable UUID id) {

        userService.deleteUser(id);

        return "User deleted successfully";
    }
}
