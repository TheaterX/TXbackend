package app.services;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import app.DTOs.LoginDTO;
import app.model.User.User;
import app.repositories.IUserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthJWT {
    private static final String SECRET_KEY = "597033733676397924423F4528482B4D6251655468576D5A7134743777217A25"; // Replace with your secret key
    private static final long EXPIRATION_TIME = 86400000; // 24 hours in milliseconds

    @Autowired
    private IUserRepository userRepository;

    private static Key getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public static String generateToken(String username, String role) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public static String extractToken(String authorizationHeader) {
        // Authorization header typically has a value like "Bearer {token}"
        String[] parts = authorizationHeader.split(" ");
        if (parts.length == 2) {
            return parts[1];
        }
        return null; // Invalid or missing token
    }

    public static Claims decodeToken(String token) {
        // Use your secret key for decoding, replace with your actual key
        return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes())).build().parseClaimsJws(token).getBody();
    }
}
