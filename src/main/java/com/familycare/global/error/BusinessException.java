package com.familycare.global.error;

import lombok.Getter;

/** 비즈니스 규칙 위반을 나타내는 예외. 서비스 계층에서 ErrorCode와 함께 던진다. */
@Getter
public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode, String detailMessage) {
        super(detailMessage);
        this.errorCode = errorCode;
    }
}
