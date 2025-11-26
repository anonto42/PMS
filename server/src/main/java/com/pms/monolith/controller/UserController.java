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

        return UserResponseDTO.fromEntity(saved);
    }

    @PostMapping("/login")
    public UserResponseDTO login(@Valid @RequestBody LoginRequestDTO loginDTO) {

        User user = userService.login(loginDTO.getEmail(), loginDTO.getPassword());

        return UserResponseDTO.fromEntity(user);
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUser(@PathVariable UUID id) {

        User user = userService.getUserById(id);

        return UserResponseDTO.fromEntity(user);
    }

    @PutMapping("/{id}")
    public UserResponseDTO updateUser(
            @PathVariable UUID id,
            @RequestParam String name
    ) {

        User updated = userService.updateUser(id, name);

        return UserResponseDTO.fromEntity(updated);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable UUID id) {

        userService.deleteUser(id);

        return "User deleted successfully";
    }
}
