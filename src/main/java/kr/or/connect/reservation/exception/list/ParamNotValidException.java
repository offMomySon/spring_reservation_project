package kr.or.connect.reservation.exception.list;

import kr.or.connect.reservation.exception.ErrorCode;

public class ParamNotValidException extends ApiCommonException {
    public ParamNotValidException() {
        super(ErrorCode.PARAM_NOT_VALID);
    }
}