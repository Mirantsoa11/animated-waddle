package com.entreprise.assurance.identityservice.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtUtils {
    private static final String SECRET = "secret_key_here";

    public String generateJwtToken(String username, Long entrepriseId) {
        return Jwts.builder()
            .setSubject(username)
            .claim("entrepriseId", entrepriseId)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 86400000))
            .signWith(SignatureAlgorithm.HS256, SECRET)
            .compact();
    }

    public Long getEntrepriseIdFromJwt(String token) {
        return ((Number) Jwts.parser()
            .setSigningKey(SECRET)
            .parseClaimsJws(token)
            .getBody()
            .get("entrepriseId")).longValue();
    }
}
