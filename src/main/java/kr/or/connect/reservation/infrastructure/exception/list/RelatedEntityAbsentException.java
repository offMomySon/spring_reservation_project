package kr.or.connect.reservation.infrastructure.exception.list;

import kr.or.connect.reservation.infrastructure.exception.ErrorCode;

public class RelatedEntityAbsentException extends ApiCommonException {
    public RelatedEntityAbsentException() {
        super(ErrorCode.RELATED_ENTITY_ABSENT);
    }
}
