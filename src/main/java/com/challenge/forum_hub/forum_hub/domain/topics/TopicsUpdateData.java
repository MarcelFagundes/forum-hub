package com.challenge.forum_hub.forum_hub.domain.topics;

import jakarta.validation.constraints.NotBlank;


public record TopicsUpdateData(
        @NotBlank
        String title,
        String message,
        TopicStatus topicStatus,
        String author,
        String course
) {
    public TopicsUpdateData(Topics data) {
        this(data.getTitle(),
                data.getMessage(),
                data.getTopicStatus(),
                data.getAuthor().getName(),
                data.getCourse());
    }
}