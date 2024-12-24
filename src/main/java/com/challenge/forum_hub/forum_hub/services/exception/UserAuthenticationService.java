package com.challenge.forum_hub.forum_hub.services.exception;

import com.challenge.forum_hub.forum_hub.repository.UserAuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService implements UserDetailsService {

    @Autowired
    private UserAuthenticationRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(repository.findByUserName(username));
        return repository.findByUserName(username);
    }
}