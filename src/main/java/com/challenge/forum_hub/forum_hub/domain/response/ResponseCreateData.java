package com.challenge.forum_hub.forum_hub.domain.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDateTime;

public record ResponseCreateData(
        @NotBlank
        String message,
        @NotBlank
        String topics,
        @NotNull
        LocalDateTime creationDate,
        @NotBlank
        String author,
        @NotBlank
        Boolean solution

) {
    public ResponseCreateData(Response response) {
        this(response.getMessage(),
                response.getTopics().getTitle(),
                response.getCreationDate(),
                response.getAuthor().getName(),
                response.getSolution());
    }
}