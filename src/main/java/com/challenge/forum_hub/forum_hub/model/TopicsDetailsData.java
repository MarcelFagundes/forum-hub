package com.challenge.forum_hub.forum_hub.model;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record TopicsDetailsData(@NotBlank
                                String title,
                                @NotBlank
                                String message,
                                LocalDateTime creation_date,
                                @NotBlank
                                TopicStatus topicStatus,
                                @NotBlank
                                String author,
                                @NotBlank
                                String course
) {
    public TopicsDetailsData(Topics topics) {
        this(topics.getTitle(), topics.getMessage(),topics.getCreation_date(), topics.getTopic_status(),
                topics.getAuthor(), topics.getCourse());
    }
}