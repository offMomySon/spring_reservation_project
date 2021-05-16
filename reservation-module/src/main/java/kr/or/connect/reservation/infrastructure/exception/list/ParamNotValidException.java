package kr.or.connect.reservation.infrastructure.exception.list;

import kr.or.connect.reservation.infrastructure.exception.ErrorCode;

public class ParamNotValidException extends ApiCommonException {
    public ParamNotValidException() {
        super(ErrorCode.PARAM_NOT_VALID);
    }
}