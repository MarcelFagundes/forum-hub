package com.challenge.forum_hub.forum_hub.services;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.challenge.forum_hub.forum_hub.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;



@Service
public class TokenService {

    private static final String ISSUER = "API forum-hub";

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        System.out.println(secret);
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(user.getUsername())
                    .withExpiresAt(dataExpiration())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("erro ao gerar token jwt", exception);
        }
    }

//    public String getSubject(String tokenJWT) {
//        try {
//            var algorithm = Algorithm.HMAC256(secret);
//            return JWT.require(algorithm)
//                    .withIssuer(ISSUER)
//                    .build()
//                    .verify(tokenJWT)
//                    .getSubject();
//        } catch (JWTVerificationException exception) {
//            throw new RuntimeException("Token JWT inválido ou expirado!");
//        }
//    }

    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado: " +tokenJWT);
        }
    }

    private Instant dataExpiration() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}