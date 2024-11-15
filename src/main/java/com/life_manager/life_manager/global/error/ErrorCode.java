package com.life_manager.life_manager.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    SAMPLE_ERROR(HttpStatus.BAD_REQUEST, "Sample Error Message"),

    // 서버 에러
    METHOD_ARGUMENT_TYPE_MISMATCH(HttpStatus.BAD_REQUEST, "Enum Type이 일치하지 않아 Binding에 실패하였습니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "지원하지 않는 HTTP method 입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류, 관리자에게 문의하세요"),

    // 휴대폰 인증 에러
    INVALID_PHONE_NUMBER(HttpStatus.BAD_REQUEST, "유효하지 않은 휴대폰 번호입니다."),
    INVALID_VERIFICATION_CODE(HttpStatus.BAD_REQUEST, "유효하지 않은 인증번호입니다."),
    EXPIRED_VERIFICATION_CODE(HttpStatus.FORBIDDEN, "인증번호가 만료되었습니다."),
    EXPIRED_VERIFICATION_SESSION(HttpStatus.FORBIDDEN, "인증 세션이 만료되었습니다."),

    // 인증 에러
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증되지 않은 사용자입니다."),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "세션이 만료되었습니다."),

    // 회원가입 및 회원정보 수정 에러
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "계정과 일치하지 않는 비밀번호입니다."),
    PASSWORD_NOT_MATCH(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    DUPLICATED_NICKNAME(HttpStatus.CONFLICT, "이미 존재하는 닉네임입니다."),
    DUPLICATED_PHONE_NUMBER(HttpStatus.CONFLICT, "이미 존재하는 전화번호입니다."),
    DUPLICATED_MEMBER_ACCOUNT(HttpStatus.CONFLICT, "이미 존재하는 계정입니다."),

    // 공고글 에러
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 게시글입니다."),
    ALREADY_APPLIED(HttpStatus.BAD_REQUEST, "이미 지원한 공고입니다."),

    // 조회 에러
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."),
    NICKNAME_IS_NULL(HttpStatus.BAD_REQUEST, "닉네임이 존재하지 않습니다."),
    PHONE_NUMBER_IS_NULL(HttpStatus.BAD_REQUEST, "전화번호가 존재하지 않습니다."),
    KAKAO_ACCOUNT_IS_NULL(HttpStatus.BAD_REQUEST, "카카오 ID가 존재하지 않습니다."),

    // ID/PW 찾기 에러
    ACCOUNT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 정보로 가입된 계정이 존재하지 않습니다."),
    INVALID_SKILL_ID(HttpStatus.BAD_REQUEST, "유효하지 않은 스킬 ID입니다.");

    private final HttpStatus status;
    private final String message;

}