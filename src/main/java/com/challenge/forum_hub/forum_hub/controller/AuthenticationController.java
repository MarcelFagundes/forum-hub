package com.challenge.forum_hub.forum_hub.controller;

import com.challenge.forum_hub.forum_hub.domain.user.AuthenticationData;
import com.challenge.forum_hub.forum_hub.domain.user.User;


import com.challenge.forum_hub.forum_hub.services.TokenService;
import com.challenge.forum_hub.forum_hub.services.security.DataTokenJWT;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

//    @PostMapping
//    public ResponseEntity loginAPI(@RequestBody @Valid AuthenticationData authenticationData) {
//
//        var authenticationToken = new UsernamePasswordAuthenticationToken(authenticationData.userName(), authenticationData.userPassword());
//        var authentication = manager.authenticate(authenticationToken);
//        var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());
//
//        return ResponseEntity.ok(new DataTokenJWT(tokenJWT));
//    }

    @PostMapping
    public ResponseEntity loginAPI(@RequestBody @Valid AuthenticationData authenticationData) {
        try {
            var authenticationToken = new UsernamePasswordAuthenticationToken(authenticationData.userName(), authenticationData.userPassword());
            var authentication = manager.authenticate(authenticationToken);

            var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());

            return ResponseEntity.ok(new DataTokenJWT(tokenJWT));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}