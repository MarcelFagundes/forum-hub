package com.challenge.forum_hub.forum_hub.domain.response;

import com.challenge.forum_hub.forum_hub.domain.topics.Topics;
import com.challenge.forum_hub.forum_hub.domain.user.User;
import com.challenge.forum_hub.forum_hub.domain.user.UserCreateData;
import jakarta.validation.constraints.NotBlank;


import java.time.LocalDateTime;

public record ResponseCreateData(
        String message,
        Topics topics,
        LocalDateTime creationDate,
        User author,
        Boolean solution

) {
    public ResponseCreateData(Response response) {
        this(response.getMessage(),
                response.getTopics(),
                response.getCreationDate(),
                response.getAuthor(),
                response.getSolution());
    }
}
