package com.challenge.forum_hub.forum_hub.repository;

import com.challenge.forum_hub.forum_hub.domain.topics.Topics;
import com.challenge.forum_hub.forum_hub.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicsRepository extends JpaRepository<Topics, Long> {

    // Retorna uma lista de tópicos com o título especificado
    List<Topics> findFirstByTitle(@NotBlank String title);

    // Retorna o primeiro tópico com o título especificado
    Topics findByTitle(@NotBlank String title);
}
