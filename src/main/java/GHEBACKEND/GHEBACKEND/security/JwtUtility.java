package GHEBACKEND.GHEBACKEND.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import GHEBACKEND.GHEBACKEND.model.User;

import java.util.*;
import java.util.function.Function;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

@Component
public class JwtUtility {

    private final String secretKey = "13f44524ad70c6a921e1a4741f63f8f9aafa3d6b6c67212be69f8197890b14bb";
    private final long jwtExpirations = 86400;
    

    // public String extractEmail(String jwtToken) {
    //     return extractClaim(jwtToken, Claims::getSubject);
    // }

    public String extractCode(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject);
    }

    public <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwtToken);
        return claimsResolver.apply(claims);
    }

    
    @SuppressWarnings("deprecation")
    private Claims extractAllClaims(String jwtToken) {
        return Jwts.parser().setSigningKey(getSigningKey()).parseClaimsJws(jwtToken).getBody();
    }

    private SecretKey getSigningKey() {
        byte [] bytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(bytes);
    }

    public boolean validateToken(String jwtToken, UserDetails userDetails) {
        // final String email = extractEmail(jwtToken);
        final String code = extractCode(jwtToken);
        return code.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken);
    }

    private boolean isTokenExpired(String jwtToken) {
        return extractExpiration(jwtToken).before(new Date());
    }

    private Date extractExpiration(String jwtToken) {
        return extractClaim(jwtToken, Claims::getExpiration);
    }

    public String generateToken(User u) {
        return createToken(u.getCode());
    }

    private String createToken(String code) {
        return Jwts.builder()
                .setSubject(code)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(getSigningKey())
                .compact();
    }


}
