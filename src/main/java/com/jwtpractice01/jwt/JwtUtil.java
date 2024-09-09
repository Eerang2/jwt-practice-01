package com.jwtpractice01.jwt;


import com.jwtpractice01.auth.LoginUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    private static final String secretKey = "93bbf089091d9e4fd7f11cf0e2abf2f3b220641c54c29ebb2120ede87efbe545";
    private final SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

    private static final Long EXPIRATION_TIME_MS = 1000 * 60 * 60 * 24L; // 밀리세컨이라 1000 * 60초 * 60분 * 24시 => 하루


    public String generateAccessToken(final LoginUser loginUser) {
        return this.generateAccessToken(loginUser, EXPIRATION_TIME_MS);
    }

    public String generateAccessToken(final LoginUser loginUser, final long expirationTimeMs) {
        String token = Jwts.builder()
                .claim("userNo", loginUser.getUserNo())
                .claim("userId", loginUser.getUserId())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationTimeMs))
                .signWith(key)
                .compact();
        return token;
    }

    /**
     *  액세스 토큰에서 로그인유저 정보 꺼내오기
     * @param accessToken
     * @return
     */
    public LoginUser getLoginUserFromAccessToken(final String accessToken) {
        Claims claims = getClaims(accessToken);

        return LoginUser.builder()
                .userNo(claims.get("userNo", Long.class))
                .userId(claims.get("userId", String.class))
                .build();
    }

    /**
     * 토큰으로부터 클레임 꺼내기
     * @param accessToken
     * @return
     */
    private Claims getClaims(final String accessToken) {
        Claims claims;

        claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(accessToken)
                .getPayload();

        return claims;
    }

}
