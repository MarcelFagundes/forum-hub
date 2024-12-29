package com.challenge.forum_hub.forum_hub.repository;

import com.challenge.forum_hub.forum_hub.domain.response.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {

    //  UserDetails findByMessage(String name);

}