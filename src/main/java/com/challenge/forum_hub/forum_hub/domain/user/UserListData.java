package com.challenge.forum_hub.forum_hub.domain.user;

import com.challenge.forum_hub.forum_hub.domain.topics.TopicStatus;
import com.challenge.forum_hub.forum_hub.domain.topics.Topics;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

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