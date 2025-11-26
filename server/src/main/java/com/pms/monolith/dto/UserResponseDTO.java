package com.pms.monolith.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
public class UserResponseDTO {

    @NotBlank(message = "Id required!")
    private UUID id;

    @NotBlank(message = "Name is required!")
    private String name;

    @NotBlank(message = "Emails required!")
    @Email(message = "Email should be valid!")
    private String email;

    @NotNull(message = "Created at is required!")
    private Date createdAt;
}
