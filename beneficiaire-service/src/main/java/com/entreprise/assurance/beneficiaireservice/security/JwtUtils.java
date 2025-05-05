package com.entreprise.assurance.beneficiaireservice.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtUtils {
    private final String jwtSecret = "secretKey";
    private final long jwtExpirationMs = 86400000;
    public String generateJwtToken(String username, Long tenantId) {
        return Jwts.builder()
            .setSubject(username)
            .claim("tenantId", tenantId)
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
    }
    public Long getTenantIdFromJwt(String token) {
        return ((Number) Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().get("tenantId")).longValue();
    }
    public String getUsernameFromJwt(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }
}
