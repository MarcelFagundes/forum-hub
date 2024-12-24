package com.challenge.forum_hub.forum_hub.domain.user;

import com.challenge.forum_hub.forum_hub.domain.topics.TopicStatus;
import com.challenge.forum_hub.forum_hub.domain.topics.Topics;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UserUpdateData( @NotBlank
                              String name,
                              @NotBlank
                              String email,
                              @NotBlank
                              String password
) {
    public UserUpdateData(User user) {
        this(user.getName(), user.getEmail(), user.getPassword());
    }
}