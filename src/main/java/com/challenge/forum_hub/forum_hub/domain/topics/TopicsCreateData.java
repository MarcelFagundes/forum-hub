package com.challenge.forum_hub.forum_hub.domain.topics;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicsCreateData(

        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotNull
        TopicStatus topicStatus,
        @NotBlank
        String author,
        @NotBlank
        String course
) {
     public TopicsCreateData(Topics topics) {
        this(topics.getTitle(), topics.getMessage(), topics.getTopic_status(),
                topics.getAuthor(), topics.getCourse());
        }
}