package com.jwtpractice01.sample;


import com.jwtpractice01.auth.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginUserController {


    @GetMapping("/echo-login-user")
    public LoginUser argumentResolver(final LoginUser loginUser) {
        return loginUser;
    }
}
