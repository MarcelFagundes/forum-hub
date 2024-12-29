package com.challenge.forum_hub.forum_hub.domain.topics;

public record TopicsUpdateData(
                               String title,
                               String message,
                               TopicStatus topicStatus,
                               String author,
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