package GHEBACKEND.GHEBACKEND.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class JwtUtils {

    private final String secretKey = "secret-key";
    private final long jwtExpirations = 86400;

    @SuppressWarnings("deprecation")
    public String generateJwtToken(String username) {

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirations))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    @SuppressWarnings("deprecation")
    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("Invalid JWT token");
        }
        return false;
    }

    @SuppressWarnings("deprecation")
    public String getUsernameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}
