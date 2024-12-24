package com.challenge.forum_hub.forum_hub.domain.topics;

import com.challenge.forum_hub.forum_hub.domain.user.User;
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
        User author,
        @NotBlank
        String course
) {
     public TopicsCreateData(Topics topics) {
        this(topics.getTitle(), topics.getMessage(), topics.getTopicStatus(),
                topics.getAuthor(), topics.getCourse());
        }
}