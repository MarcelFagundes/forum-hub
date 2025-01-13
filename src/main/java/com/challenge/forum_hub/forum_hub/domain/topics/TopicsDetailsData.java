package com.challenge.forum_hub.forum_hub.domain.topics;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record TopicsDetailsData(@NotNull
                                Long id,
                                @NotBlank
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
        this(topics.getId(),
                topics.getTitle(),
                topics.getMessage(),
                topics.getCreationDate(),
                topics.getTopicStatus(),
                topics.getAuthor().getName(),
                topics.getCourse());
    }
}