package com.challenge.forum_hub.forum_hub.domain.response;

import com.challenge.forum_hub.forum_hub.domain.topics.Topics;
import com.challenge.forum_hub.forum_hub.domain.user.User;
import jakarta.validation.constraints.NotBlank;

public record ResponseCreateData(

        @NotBlank
        String message,

        @NotBlank
        Topics topics,

        @NotBlank
        User author,

        @NotBlank
        Boolean solution
) {
     public ResponseCreateData(Response user) {
        this(user.getMessage(), user.getTopics(), user.getAuthor(), user.getSolution());
     }
}