package com.pms.monolith.dto;

import com.pms.monolith.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
public class UserResponseDTO {

    private UUID id;
    private String name;
    private String email;
    private Date createdAt;

    public static UserResponseDTO fromEntity(User user) {
        if (user == null) return null;

        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
