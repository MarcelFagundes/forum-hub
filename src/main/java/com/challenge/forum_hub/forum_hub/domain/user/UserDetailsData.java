package com.challenge.forum_hub.forum_hub.domain.user;

import com.challenge.forum_hub.forum_hub.domain.topics.TopicStatus;
import com.challenge.forum_hub.forum_hub.domain.topics.Topics;
import jakarta.validation.constraints.NotBlank;

public record UserDetailsData( @NotBlank
                               String name,
                               @NotBlank
                               String email,
                               @NotBlank
                               String password
) {
    public UserDetailsData(User user) {
        this(user.getName(), user.getEmail(), user.getPassword());
    }
}