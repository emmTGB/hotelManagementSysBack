package com.oxlxs.hotelmanagementsysback.security;

import com.oxlxs.hotelmanagementsysback.entity.EmployeeRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtProvider {

    private static final String SECRET_KEY = "thisIsAVeryLongSecretKeyThatIs256BitsLong12345";
    private static final long ACCESS_TOKEN_EXPIRATION_TIME = 60*60*1000;  // access 一小时过期
    private static final long REFRESH_TOKEN_EXPIRATION_TIME = 7*24*60*60*1000;  // refresh 七天过期，自带续期

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET_KEY));
    }


    // 生成 JWT Token
    public String createEmployeeToken(String username, EmployeeRole role) {
        return Jwts.builder()
                .subject(username)
                .claim("role", role.name())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 24 * 3600000))  // 设置 24 小时过期时间
                .signWith(getSigningKey())
                .compact();
    }

    // 验证 JWT Token
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())  // 使用字节数组密钥
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 从 Token 中获取用户名
    public String getUsernameFromToken(String token) {
            return Jwts.parser()
                    .verifyWith(getSigningKey())  // 使用字节数组密钥
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
    }

    public Jws<Claims> extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token);
    }

    public String getRoleFromToken(String token) {
        return extractClaims(token).getPayload().get("role").toString();
    }
}
