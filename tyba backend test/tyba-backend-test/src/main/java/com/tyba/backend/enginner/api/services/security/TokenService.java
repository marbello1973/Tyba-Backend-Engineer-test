package com.tyba.backend.enginner.api.services.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tyba.backend.enginner.api.modelo.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.services.security.secret}")
    private String JWT_SECRET;

    public String generarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            return JWT.create()
                    .withIssuer("tyba")
                    .withSubject(usuario.getEmail())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }

    public String getSubject(String token) throws RuntimeException {

        if(token == null) throw new RuntimeException();

        DecodedJWT decodedJWT = null;

        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            decodedJWT = JWT.require(algorithm)
                    .withIssuer("tyba")
                    .build()
                    .verify(token);
        } catch (JWTVerificationException exception) {
            System.out.println(exception.toString());
        }

        if (decodedJWT == null || decodedJWT.getSubject() == null) {
            throw new RuntimeException("Token invalido");
        }
        return decodedJWT.getSubject();
    }


    private Instant generarFechaExpiracion(){
        //return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.UTC);
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}
