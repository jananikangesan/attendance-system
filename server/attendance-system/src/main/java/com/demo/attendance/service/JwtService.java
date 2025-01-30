package com.demo.attendance.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;


@Service
public class JwtService {

    private Logger logger = LoggerFactory.getLogger(JwtService.class);

    private final String SECRET_KEY="be347d644eddf843fd6cc27298e1214326db69cfb7669601365434e3a24233ca";


    public String generateToken(String username){
        logger.info("Generating token for user: {}", username);
        String token= Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1*60*60*1000))
                .signWith(getSigninKey())
                .compact();

        logger.info("Generated token: {}", token);

        return token;
    }

    private SecretKey getSigninKey(){

        logger.info("Getting signing key");

        byte[] keyBytes= Decoders.BASE64URL.decode(SECRET_KEY);

        return Keys.hmacShaKeyFor(keyBytes);
    }


    public boolean isValid(String token){
        logger.info("Validating token for user: {}",token);
        String username=extractUsername(token);
        boolean isValid = isTokenExpired(token);
        logger.info("Token validity for user {}: {}", isValid);
        return (isValid);
    }


    private boolean isTokenExpired(String token){
        boolean expired = extractExpiration(token).before(new Date());
        logger.info("Is token expired? {}", expired);
        return expired;
    }

    private Date extractExpiration(String token) {
        logger.info("Extracting expiration from token");
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractUsername(String token){
        logger.info("Extracting username from token");
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims,T> resolver){
        logger.info("Extracting claims from token");
        Claims claims=extractALLClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractALLClaims(String token){
        logger.info("Extracting all claims from token");
        return Jwts.parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
