package com.challenge.forum_hub.forum_hub.domain.response;

import com.challenge.forum_hub.forum_hub.domain.topics.Topics;
import com.challenge.forum_hub.forum_hub.domain.user.User;
import jakarta.validation.constraints.NotBlank;

public record ResponseUpdateData(@NotBlank
                                 String message,
//                                 String topics,
                                 String author,
                                 Boolean solution
) {
    public ResponseUpdateData(Response data) {
        this(   data.getMessage(),
//                data.getTopics().getMessage(),
                data.getAuthor().getName(),
                data.getSolution());
    }
}