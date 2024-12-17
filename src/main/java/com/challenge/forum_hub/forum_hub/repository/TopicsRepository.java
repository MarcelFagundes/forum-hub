package com.challenge.forum_hub.forum_hub.repository;

import com.challenge.forum_hub.forum_hub.domain.topics.Topics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicsRepository extends JpaRepository<Topics, Long> {
}
