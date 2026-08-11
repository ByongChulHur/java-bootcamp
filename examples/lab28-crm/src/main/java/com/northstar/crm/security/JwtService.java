package com.northstar.crm.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
  private static final long EXPIRATION_MS = 60 * 60 * 1000; // 1 hour
  private final SecretKey key;

  public JwtService(@Value("${northstar.security.jwt-secret}") String secret) {
    this.key = Keys.hmacShaKeyFor(secret.getBytes());
  }

  public String issueToken(String subject, String role) {
    Date now = new Date();
    Date expiry = new Date(now.getTime() + EXPIRATION_MS);
    return Jwts.builder()
            .subject(subject)
            .claim("role", role)
            .issuedAt(now)
            .expiration(expiry)
            .signWith(key)
            .compact();
  }

  public String parseSubject(String token) {
    return parseClaims(token).getSubject();
  }

  public String parseRole(String token) {
    return parseClaims(token).get("role", String.class);
  }

  private Claims parseClaims(String token) {
    return Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .getPayload();
  }
}