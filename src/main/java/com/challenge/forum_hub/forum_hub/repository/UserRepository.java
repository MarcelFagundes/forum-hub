package com.challenge.forum_hub.forum_hub.repository;

import com.challenge.forum_hub.forum_hub.domain.user.UserAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserAuthentication, Long> {

  UserDetails findByUserName(String username);

}