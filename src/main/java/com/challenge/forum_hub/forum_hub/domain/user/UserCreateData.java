package com.challenge.forum_hub.forum_hub.domain.user;

import jakarta.validation.constraints.NotBlank;

public record UserCreateData(

        @NotBlank
        String name,
        @NotBlank
        String email,
        @NotBlank
        String password
) {
    public UserCreateData(User user) {
        this(user.getName(), user.getEmail(), user.getPassword());
    }
}