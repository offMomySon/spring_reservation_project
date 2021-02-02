package kr.or.connect.reservation.exception.list;

import kr.or.connect.reservation.exception.ErrorCode;

public class ApiCommonException extends RuntimeException {
    private ErrorCode errorCode;

    public ApiCommonException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
