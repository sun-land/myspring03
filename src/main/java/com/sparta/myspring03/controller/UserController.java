package com.sparta.myspring03.controller;

import com.sparta.myspring03.requestDto.SignupRequestDto;
import com.sparta.myspring03.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/user/signup")
    public void signup(@RequestBody SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
    }

    // 로그인
//    @GetMapping("/user/login")
//    public String login() {
//        return "로그인 성공";
//    }
}
