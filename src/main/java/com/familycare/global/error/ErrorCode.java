package com.familycare.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * 서비스 전체에서 쓰는 오류 코드. 도메인이 생기면 여기에 추가한다.
 * 예: GROUP_NOT_MEMBER(HttpStatus.FORBIDDEN, "G001", "이 가족 그룹의 구성원이 아닙니다.")
 */
@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 공통
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "C001", "입력값이 올바르지 않습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "C002", "요청한 리소스를 찾을 수 없습니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "C003", "지원하지 않는 요청 방식입니다."),
    CONCURRENT_MODIFICATION(HttpStatus.CONFLICT, "C004", "다른 가족이 먼저 수정했습니다. 새로고침 후 다시 시도해 주세요."),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C999", "서버 오류가 발생했습니다."),

    // 인증·권한
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "A001", "로그인이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "A002", "접근 권한이 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
