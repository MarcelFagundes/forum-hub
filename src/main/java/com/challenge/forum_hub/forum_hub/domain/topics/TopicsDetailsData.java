package com.challenge.forum_hub.forum_hub.domain.topics;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record TopicsDetailsData(@NotBlank
                                String title,
                                @NotBlank
                                String message,
                                LocalDateTime creationDate,
                                @NotBlank
                                TopicStatus topicStatus,
                                @NotBlank
                                String author,
                                @NotBlank
                                String course
) {
    public TopicsDetailsData(Topics topics) {
        this(topics.getTitle(), topics.getMessage(),topics.getCreationDate(), topics.getTopicStatus(),
                topics.getAuthor(), topics.getCourse());
    }
}