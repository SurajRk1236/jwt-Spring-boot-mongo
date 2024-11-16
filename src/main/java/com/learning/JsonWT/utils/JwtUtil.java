package com.learning.JsonWT.utils;

import com.learning.JsonWT.config.AppConfig;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Autowired
    AppConfig appConfig;

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(appConfig.getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String userId, boolean isRefreshToken) {
        long expiration = isRefreshToken ? appConfig.getRefreshTime() : appConfig.getAccessTime();
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, getSignInKey())
                .compact();
    }

    public Date getExpirationDate(String token) {
        return Jwts.parser().setSigningKey(getSignInKey()).parseClaimsJws(token).getBody().getExpiration();
    }

    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(getSignInKey()).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean isTokenExpired(String token) {
        return Jwts.parser().setSigningKey(getSignInKey()).parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }
}
