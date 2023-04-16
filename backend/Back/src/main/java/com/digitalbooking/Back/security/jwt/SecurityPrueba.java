package com.digitalbooking.Back.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

public class SecurityPrueba {
    private static final String SECRET_KEY = "mi_secreto";
    private static final long EXPIRATION_TIME = 864_000_000; // 10 días

    public static String generateToken(String username, String role) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}