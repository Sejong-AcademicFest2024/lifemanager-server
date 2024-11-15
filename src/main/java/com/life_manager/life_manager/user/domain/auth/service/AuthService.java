package com.life_manager.life_manager.user.domain.auth.service;

import com.life_manager.life_manager.user.domain.SignInRequest;
import com.life_manager.life_manager.user.domain.auth.dto.SignInResponse;
import com.life_manager.life_manager.user.domain.auth.dto.VerificationCodeMessageResponse;
import com.life_manager.life_manager.user.domain.auth.dto.VerificationRequest;
import com.life_manager.life_manager.user.domain.auth.response.VerificationResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private static final String VERIFICATION_CODE_MESSAGE = "본인확인 인증번호 [%s]를 화면에 입력해주세요.";

    private static final String VERIFICATION_CODE_SESSION_ATTRIBUTE = "verificationCode";
    private static final String VERIFICATION_SESSION_ATTRIBUTE = "verified";

    private static final int SESSION_TIME_OUT_SEC_FOR_VERIFICATION = 60 * 5; // 5분
    private static final int SESSION_TIME_OUT_AFTER_VERIFICATION = 60 * 10; // 10분

    private final SmsService smsService;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    private final WorkerRepository workerRepository;

    @Transactional(readOnly = true)
    public VerificationCodeMessageResponse sendVerificationCode(VerificationCodeMessageRequest request,
                                                                HttpSession session) throws
            NurigoMessageNotReceivedException,
            NurigoEmptyResponseException,
            NurigoUnknownException {
        if (workerRepository.existsByPhoneNumber(request.receiver())) {
            throw new CustomException(DUPLICATED_PHONE_NUMBER);
        }

        String verificationCode = generateVerificationCode();

        setVerificationSession(verificationCode, session);
        smsService.sendMessage(request.receiver(), String.format(VERIFICATION_CODE_MESSAGE, verificationCode));

        return VerificationCodeMessageResponse.success();
    }

    private String generateVerificationCode() {
        return String.valueOf((int)(Math.random() * Math.pow(10, VERIFICATION_CODE_LENGTH)));
    }

    private void setVerificationSession(String verificationCode, HttpSession session) {
        session.setMaxInactiveInterval(SESSION_TIME_OUT_SEC_FOR_VERIFICATION);
        session.setAttribute(VERIFICATION_CODE_SESSION_ATTRIBUTE, verificationCode);
        session.setAttribute(VERIFICATION_SESSION_ATTRIBUTE, false);
    }

    public VerificationResponse verify(VerificationRequest request, HttpSession session) {
        String verificationCode = (String)session.getAttribute(VERIFICATION_CODE_SESSION_ATTRIBUTE);

        if (verificationCode == null) {
            throw new CustomException(EXPIRED_VERIFICATION_CODE);
        }

        if (!verificationCode.equals(request.verificationCode())) {
            throw new CustomException(INVALID_VERIFICATION_CODE);
        }

        setVerifiedSession(session);

        return VerificationResponse.success();
    }

    private void setVerifiedSession(HttpSession session) {
        session.setMaxInactiveInterval(SESSION_TIME_OUT_AFTER_VERIFICATION);
        session.removeAttribute(VERIFICATION_CODE_SESSION_ATTRIBUTE);
        session.setAttribute(VERIFICATION_SESSION_ATTRIBUTE, true);
    }

    public void validateVerificationSession(HttpSession session) {
        if (session.isNew()) {
            throw new CustomException(EXPIRED_VERIFICATION_SESSION);
        }

        if (session.getAttribute(VERIFICATION_SESSION_ATTRIBUTE) == null || !(boolean)session.getAttribute(
                VERIFICATION_SESSION_ATTRIBUTE)) {
            throw new CustomException(EXPIRED_VERIFICATION_SESSION);
        }
    }

    public SignInResponse signIn(SignInRequest request) {
        Worker worker = workerRepository.findByMemberAccount(request.memberAccount())
                .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));

        if (!passwordEncoder.matches(request.password(), worker.getPassword())) {
            throw new CustomException(INVALID_PASSWORD);
        }

        return new SignInResponse(jwtProvider.generateAccessToken(worker.getId()));
    }
}