package GHEBACKEND.GHEBACKEND.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import java.util.*;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

@Component
public class JwtUtils {

    private final String secretKey = "13f44524ad70c6a921e1a4741f63f8f9aafa3d6b6c67212be69f8197890b14bb";
    private final long jwtExpirations = 86400;

    // @SuppressWarnings("deprecation")
    public String generateJwtToken(String username) {

        // Generate key from secret string 
        Key key = new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS256.getJcaName());


        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirations))
                .signWith(key, SignatureAlgorithm.HS256) // Non-deprecated method
                // .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    // @SuppressWarnings("deprecation")
    @SuppressWarnings("deprecation")
    public boolean validateJwtToken(String token) {
    try {
            // Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("Invalid JWT token " + token); 
        }
        return false;
    }
    // public Claims validateJwtToken(String token) {
    //     Key key = new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS256.getJcaName());

    //     return Jwts.parserBuilder()
    //             .setSigningKey(key)  // Use the SecretKey instead of the deprecated String method
    //             .build()
    //             .parseClaimsJws(token)
    //             .getBody();
    // }

    @SuppressWarnings("deprecation")
    public String getUsernameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}
