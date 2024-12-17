package com.challenge.forum_hub.forum_hub.domain.topics;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TopicsUpdateData(
                               @NotNull
                               Long id,
                               //@NotBlank
                               String title,
                               //@NotBlank
                               String message,
                               LocalDateTime creation_date,
                               //@NotBlank
                               TopicStatus topicStatus,
                               //@NotBlank
                               String author,
                               //@NotBlank
                               String course
) {
    public TopicsUpdateData(Topics topics) {
//        this(topics.getTitle(), topics.getMessage(),topics.getCreation_date(), topics.getTopic_status(),
//                topics.getAuthor(), topics.getCourse());
        this(topics.getId(), topics.getTitle(), topics.getMessage(),topics.getCreation_date(),
             topics.getTopic_status(), topics.getAuthor(), topics.getCourse());
    }
}