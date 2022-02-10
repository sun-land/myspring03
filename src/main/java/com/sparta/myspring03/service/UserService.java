package com.sparta.myspring03.service;

import com.sparta.myspring03.model.User;
import com.sparta.myspring03.model.UserRoleEnum;
import com.sparta.myspring03.repository.UserRepository;
import com.sparta.myspring03.requestDto.SignupRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final String ADMIN_TOKEN = "master";

    // 회원가입
    public void signup(SignupRequestDto signupRequestDto) {

        // 중복가입 체크
        String username = signupRequestDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }

        // 패스워드 암호화
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        signupRequestDto.setEncodedPassword(password);

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (signupRequestDto.isAdmin()) {
            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }
        signupRequestDto.setRole(role);

        // User 저장
        User user = new User(signupRequestDto);
        userRepository.save(user);

    }
}
