package com.challenge.forum_hub.forum_hub.controller;



import com.challenge.forum_hub.forum_hub.domain.userauthentication.UserAuthenticationData;
import com.challenge.forum_hub.forum_hub.domain.userauthentication.UserAuthentication;
import com.challenge.forum_hub.forum_hub.services.TokenService;
import com.challenge.forum_hub.forum_hub.services.security.DataTokenJWT;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity loginAPI(@RequestBody @Valid UserAuthenticationData userAuthenticationData) {
        try {
            var authenticationToken = new UsernamePasswordAuthenticationToken(userAuthenticationData.userName(), userAuthenticationData.userPassword());
            var authentication = manager.authenticate(authenticationToken);

            var tokenJWT = tokenService.generateToken((UserAuthentication) authentication.getPrincipal());

            return ResponseEntity.ok(new DataTokenJWT(tokenJWT));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}