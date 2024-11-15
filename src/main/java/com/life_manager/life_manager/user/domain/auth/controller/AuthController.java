package com.life_manager.life_manager.user.domain.auth.controller;

import com.life_manager.life_manager.user.domain.SignInRequest;
import com.life_manager.life_manager.user.domain.auth.dto.SignInResponse;
import com.life_manager.life_manager.user.domain.auth.dto.VerificationCodeMessageRequest;
import com.life_manager.life_manager.user.domain.auth.dto.VerificationCodeMessageResponse;
import com.life_manager.life_manager.user.domain.auth.dto.VerificationRequest;
import com.life_manager.life_manager.user.domain.auth.response.VerificationResponse;
import com.life_manager.life_manager.user.domain.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "인증 코드 전송", description = "인증 코드를 전송합니다. 인증 코드는 6자리 숫자입니다. 예시: 123456")
    @PostMapping("/send-code")
    public VerificationCodeMessageResponse sendVerificationCode(@RequestBody VerificationCodeMessageRequest request, HttpServletRequest servletRequest) throws
            NurigoMessageNotReceivedException,
            NurigoEmptyResponseException,
            NurigoUnknownException {
        HttpSession session = servletRequest.getSession();

        return authService.sendVerificationCode(request, session);
    }

    @Operation(summary = "인증 코드 확인", description = "인증 코드를 확인합니다.")
    @PostMapping("/verify")
    public VerificationResponse verifyCode(@RequestBody VerificationRequest request, HttpServletRequest servletRequest) {

        HttpSession session = servletRequest.getSession();

        return authService.verify(request, session);
    }

    @Operation(summary = "로그인", description = "로그인을 진행 후, Access Token을 발급받습니다.")
    @PostMapping("/sign-in")
    public SignInResponse signIn(@RequestBody SignInRequest request) {
        return authService.signIn(request);
    }
}