package com.challenge.forum_hub.forum_hub.model;

import jakarta.validation.constraints.NotBlank;

public record TopicsCreateData(

        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotBlank
        TopicStatus topicStatus,
        @NotBlank
        String author,
        @NotBlank
        String course
) {}