package GHEBACKEND.GHEBACKEND.security.Config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.security.Jwt.JwtRepository;
import GHEBACKEND.GHEBACKEND.security.Utilisateur.Utilisateur;
import GHEBACKEND.GHEBACKEND.security.Utilisateur.UtilisateurService;
import GHEBACKEND.GHEBACKEND.utils.Utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final UtilisateurService utilisateurService;
    private final String ENCRYPTION_KEY = "dd5e9770f0a84810f98cdf97b96c99fc9c928643b3a46394ce0a02ef6dd7df9f";
    private final String BEARER = "bearer";
    private final JwtRepository jwtRepository;

    public Map<String, String> generateJwt(String username){
        Utilisateur utilisateur = this.utilisateurService.loadUserByUsername(username);
        Map<String,String> jwtMap =   this.generateJwt(utilisateur);
    
        return jwtMap;
    }
    
    public GHEBACKEND.GHEBACKEND.security.Jwt.Jwt tokenByValue(String value){
        return jwtRepository.findByValueAndDesactiveAndExpire(
                    value,
                    false,
                    false)
                .orElseThrow(
                    () -> new  EntityNotFoundException("Ce token n'est pas valide")
                );
    }

    public Map<String,String> generateJwt(Utilisateur utilisateur){
        final long currentTime = System.currentTimeMillis();
        final long currentTimeExpiration = currentTime + 30 * 60 * 1000;
        Map<String,Object> claims = new HashMap<>();
        claims.put("nom",Utils.concatWithSpace(utilisateur.getUtiNom(),utilisateur.getUtiPrenom()));
        claims.put("username", utilisateur.getUsername());
        claims.put(Claims.EXPIRATION, currentTimeExpiration);
        claims.put(Claims.ISSUED_AT, currentTime);
        claims.put(Claims.SUBJECT, utilisateur.getUtiEmail());

        return Map.of(
            BEARER,
            Jwts.builder()
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(currentTimeExpiration))
                .setSubject(utilisateur.getUsername())
                .setClaims(claims)
                .signWith(getKey(),SignatureAlgorithm.HS256)
                .compact()
        );
    }

    private Key getKey(){
        final byte[] decoders = Decoders.BASE64.decode(ENCRYPTION_KEY);
        return Keys.hmacShaKeyFor(decoders);
    }

    public String extractUsername(String token){
        return this.getClaim(token,Claims::getSubject);
    }

    public boolean isTokenExpired(String token){
        Date expirationDate = this.getClaim(token, Claims::getExpiration);
        return expirationDate.before(new Date());
    }

    private <T> T getClaim(String token,Function<Claims,T> function){
        Claims claims = getClaims(token);
        return function.apply(claims);
    }

    private Claims getClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    } 
}
