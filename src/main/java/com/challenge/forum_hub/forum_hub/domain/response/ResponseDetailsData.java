package com.challenge.forum_hub.forum_hub.domain.response;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public record ResponseDetailsData(Long id,
                                  @NotBlank
                                  String message,

                                  @NotBlank
                                  String topics,

                                  LocalDateTime creationDate,

                                  @NotBlank
                                  String author,

                                  @NotBlank
                                  Boolean solution
) {
    public ResponseDetailsData(Response data) {
        this(data.getId(),
                data.getMessage(),
                data.getTopics().getMessage(),
                data.getCreationDate(),
                data.getAuthor().getName(),
                data.getSolution());
    }
}