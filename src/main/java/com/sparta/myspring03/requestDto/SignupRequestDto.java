package com.sparta.myspring03.requestDto;

import com.sparta.myspring03.model.UserRoleEnum;
import lombok.Getter;

@Getter
public class SignupRequestDto {
    private String username;
    private String password;
    private String nickname;
    private boolean admin;
    private String adminToken;
    private UserRoleEnum role;

    // 패스워드 암호화
    public void setEncodedPassword(String password) {
        this.password = password;
    }

    // 권한 설정
    public void setRole(UserRoleEnum role) {
        this.role = role;
    }
}
