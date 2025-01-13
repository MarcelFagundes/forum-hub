package com.challenge.forum_hub.forum_hub.domain.user;

public record UserUpdateData(
        String name,
        String email,
        String password
) {
    public UserUpdateData(User user) {
        this(user.getName(), user.getEmail(), user.getPassword());
    }
}