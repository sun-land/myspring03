package com.sparta.myspring03.model;

import com.sparta.myspring03.requestDto.SignupRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class User {

    @GeneratedValue
    @Id
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING) // 쓸 때는 Enum으로 쓰지만 db저장될 때는 String으로 쓴다.
    private UserRoleEnum role;

    public User(SignupRequestDto signupRequestDto) {
        this.username = signupRequestDto.getUsername();
        this.password = signupRequestDto.getPassword();
        this.nickname = signupRequestDto.getNickname();
        this.role = signupRequestDto.getRole();
    }

}
