package com.jwtpractice01.jwt;

import com.jwtpractice01.auth.LoginUser;
import com.jwtpractice01.common.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class JwtUtilTest extends BaseTest {

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    void 토큰생성해보기() {
        String newToken = jwtUtil.generateAccessToken(new LoginUser(7L, "jinwoo"));
        Assertions.assertNotEquals("", newToken);
    }

    @Test
    void 토큰에서_회원번호_꺼내보기() {
        String accessToken = jwtUtil.generateAccessToken(new LoginUser(7L, "jinwoo"));
    }
}
