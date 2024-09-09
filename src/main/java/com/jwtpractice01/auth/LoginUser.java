package com.jwtpractice01.auth;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LoginUser {

    private Long userNo;
    private String userId;

    @Builder
    public LoginUser(Long userNo, String userId) {
        this.userNo = userNo;
        this.userId = userId;
    }
}
