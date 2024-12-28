package com.challenge.forum_hub.forum_hub.domain.response;

import com.challenge.forum_hub.forum_hub.domain.response.Response;
import com.challenge.forum_hub.forum_hub.domain.topics.Topics;
import com.challenge.forum_hub.forum_hub.domain.user.User;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record ResponseListData(

        Long id,
        //@NotBlank
        String message,

        //@NotBlank
        Topics topics,

        LocalDateTime creationDate,

        //@NotBlank
        User author,

        //@NotBlank
        Boolean solution
) {
    public ResponseListData(Response data) {
        this(data.getId(),
                data.getMessage(),
                data.getTopics(),
                data.getCreationDate(),
                data.getAuthor(),
                data.getSolution());
    }

}