package com.life_manager.life_manager.global.exception;

import com.life_manager.life_manager.global.error.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomException extends RuntimeException {
    private ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
