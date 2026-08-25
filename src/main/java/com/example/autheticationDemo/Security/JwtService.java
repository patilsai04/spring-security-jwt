package com.example.autheticationDemo.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {
    private final String SECRET_KEY =
            "my-super-secret-key-my-super-secret-key";

    private SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(
                SECRET_KEY.getBytes(StandardCharsets.UTF_8)
        );
    }

   public String generateToken(String username){
       return Jwts.builder()
               .subject(username)
               .issuedAt(new Date())
               .expiration(new Date(System.currentTimeMillis()+1000*60*15))
               .signWith(getSigningKey())
               .compact();
   }

   public String extractUsername(String token){
        return extractAllClaims(token).getSubject();
   }

   private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
   }
}
