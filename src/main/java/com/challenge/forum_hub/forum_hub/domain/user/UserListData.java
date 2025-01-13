package com.challenge.forum_hub.forum_hub.domain.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserListData(
        @NotNull
        Long id,
        @NotBlank
        String name,
        @NotBlank
        String email,
        @NotBlank
        String password
) {
    public UserListData(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }
}