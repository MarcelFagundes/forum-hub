package com.challenge.forum_hub.forum_hub.domain.topics;

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
) {
    public TopicsCreateData(Topics topics) {
        this(topics.getTitle(),
                topics.getMessage(),
                topics.getTopicStatus(),
                topics.getAuthor().getName(),
                topics.getCourse());
    }
}