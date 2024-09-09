package com.jwtpractice01.sample;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwtpractice01.auth.LoginUser;
import com.jwtpractice01.common.BaseMockMvcTest;
import com.jwtpractice01.jwt.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class LoginUserControllerTest extends BaseMockMvcTest {


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    void 토큰_생성해서_해더에_토큰넣고_에코컨트롤러_호출해서_정상인지_확인하기() throws Exception {
        LoginUser loginUser = new LoginUser(7L, "jinwoo");

        String accessToken = jwtUtil.generateAccessToken(loginUser);
        final ResultActions actions = mockMvc.perform(get("/echo-login-user")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + accessToken)
        );

        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.userNo").value(loginUser.getUserNo()))
                .andExpect(jsonPath("$.userId").value(loginUser.getUserId()));
    }
}