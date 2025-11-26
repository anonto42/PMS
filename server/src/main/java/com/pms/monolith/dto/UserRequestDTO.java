package com.pms.monolith.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class UserRequestDTO {

    @NotBlank(message = "Name is required!")
    @Size(min = 2, max = 50, message = "Name must be 2-50 characters!")
    private String name;

    @NotBlank(message = "Email is required!")
    @Email(message = "Email should be valid!")
    private String email;

    @NotBlank(message = "Password is required!")
    @Size(min = 6, max = 50, message = "Password must be at least 6 characters!")
    private String password;

}
