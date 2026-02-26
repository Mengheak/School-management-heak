package com.example.school_mgnt_sb.services.auth;


import com.example.school_mgnt_sb.models.AuthUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Service
public class JwtService {
    @Getter
    private final SecretKey key;
    private final String issuer;
    private final int accessMinutes;

    public JwtService(@Value("${config.security.jwt.secret}") String secret,
                      @Value("${config.security.jwt.issuer}") String issuer,
                      @Value("${config.security.jwt.access-token-minutes}") int accessMinutes){
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.issuer = issuer;
        this.accessMinutes = accessMinutes;
    }

    public String generateAccessToken(AuthUser user){
        Instant now = Instant.now();
        Instant exp = now.plus(accessMinutes, ChronoUnit.MINUTES);

        List<String> roles = user.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority).toList();

        return Jwts.builder()
                .setIssuer(issuer)
                .setSubject(user.getUsername())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(exp))
                .claim("uid", user.getId().toString())
                .claim("roles", roles)
                .signWith(key)
                .compact();
    }

    public Boolean validateToken (String token, UserDetails userDetails){
            boolean isExpired = this.extractExpiration(token).before(new Date());
            String username = this.extractUsername(token);

            return !isExpired && username.equals(userDetails.getUsername());
    }

    public Jws<Claims> parse(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .requireIssuer(issuer)
                .build()
                .parseClaimsJws(token);
    }


    public Date extractExpiration(String token) {
        return this.extractClaim(token,Claims::getExpiration);
    }

    public String extractUsername(String token){
        return this.extractClaim(token, Claims::getSubject);
    }


    private <T> T extractClaim(String token, Function<Claims, T> claimResolver){
        Claims claims = this.extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
