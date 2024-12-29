package com.challenge.forum_hub.forum_hub.domain.topics;

import jakarta.validation.constraints.NotNull;

public record TopicsUpdateData(
        @NotNull
        String title,
        @NotNull
        String message,
        @NotNull
        TopicStatus topicStatus,
        @NotNull
        String author,
        @NotNull
        String course
) {
    public TopicsUpdateData(Topics topics) {
        this(topics.getTitle(),
                topics.getMessage(),
                topics.getTopicStatus(),
                topics.getAuthor().getName(),
                topics.getCourse());
    }
}