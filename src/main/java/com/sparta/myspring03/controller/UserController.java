package com.sparta.myspring03.controller;

import com.sparta.myspring03.requestDto.SignupRequestDto;
import com.sparta.myspring03.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/user/signup")
    public void signup(@RequestBody SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
    }

}
