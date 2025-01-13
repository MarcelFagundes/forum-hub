package com.challenge.forum_hub.forum_hub.domain.response;

import jakarta.validation.constraints.NotBlank;

public record ResponseUpdateData(@NotBlank
                                 String message,
                                 String topics,
                                 String author,
                                 Boolean solution
) {
    public ResponseUpdateData(Response data) {
        this(data.getMessage(),
                data.getTopics().getTitle(),
                data.getAuthor().getName(),
                data.getSolution());
    }
}