package com.challenge.forum_hub.forum_hub.domain.topics;

import com.challenge.forum_hub.forum_hub.domain.user.User;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TopicsUpdateData(
                               @NotNull
                               Long id,
                               //@NotBlank
                               String title,
                               //@NotBlank
                               String message,
                               LocalDateTime creationDate,
                               //@NotBlank
                               TopicStatus topicStatus,
                               //@NotBlank
                               User author,
                               //@NotBlank
                               String course
) {
    public TopicsUpdateData(Topics topics) {
        this(topics.getId(), topics.getTitle(), topics.getMessage(),topics.getCreationDate(),
             topics.getTopicStatus(), topics.getAuthor(), topics.getCourse());
    }
}