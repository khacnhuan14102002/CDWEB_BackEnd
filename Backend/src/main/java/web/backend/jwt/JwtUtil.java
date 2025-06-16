package web.backend.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import web.backend.model.KhachHang;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final long expirationMillis = 24 * 60 * 60 * 1000;
    // 1 ngày
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(KhachHang khachHang) {
        return Jwts.builder()
                .setSubject(khachHang.getEmail())
                .claim("role", khachHang.getRole())
                .claim("id", khachHang.getMaKH())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(key)
                .compact();
    }

    public String extractEmail(String token) {
        return parseClaims(token).getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}