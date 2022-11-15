package com.ifpb.dac.projetospring.business.service.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ifpb.dac.projetospring.business.service.TokenService;
import com.ifpb.dac.projetospring.model.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenServiceImpl implements TokenService {

    public static final String CLAIM_USERID = "userid";
    public static final String CLAIM_USERNAME = "username";
    public static final String CLAIM_EXPIRES_AT = "expiresIn";

    @Value("${jwt.expiration}")
    private String expiration;

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public String generate(User user) {
        long expirationMinutes = Long.parseLong(this.expiration);
        LocalDateTime expirationLocalDateTime = LocalDateTime.now().plusMinutes(expirationMinutes);
        Date expirationDate = Date.from(Instant.now().plusSeconds(expirationMinutes * 60l));

        String tokenExpiration = expirationLocalDateTime.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"));

        return Jwts.builder()
                .setExpiration(expirationDate)
                .setSubject(user.getId().toString())
                .claim(CLAIM_USERID, user.getId())
                .claim(CLAIM_USERNAME, user.getUsername())
                .claim(CLAIM_EXPIRES_AT, tokenExpiration)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    @Override
    public Claims getClaims(String token) throws ExpiredJwtException {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    @Override
    public boolean isValid(String token) {
        if (token == null) {
            return false;
        }

        try {
            Claims claims = getClaims(token);
            LocalDateTime expirationDate = claims.getExpiration().toInstant().atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            return !LocalDateTime.now().isAfter(expirationDate);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getUsername(String token) {
        return (String) getClaims(token).get(CLAIM_USERNAME);
    }

    @Override
    public Long getUserId(String token) {
        return Long.parseLong(getClaims(token).getSubject());
    }

    @Override
    public String get(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");

        return (authorization == null || !authorization.startsWith("Bearer"))
                ? null
                : authorization.split(" ")[1];
    }

}
