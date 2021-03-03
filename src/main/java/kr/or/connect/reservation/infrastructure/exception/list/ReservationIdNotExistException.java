package kr.or.connect.reservation.infrastructure.exception.list;

import kr.or.connect.reservation.infrastructure.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ReservationIdNotExistException extends ApiCommonException {
    private long reservationId;

    public ReservationIdNotExistException(long reservationId) {
        super(ErrorCode.RESERVATION_ID_NOT_EXIST);
        this.reservationId = reservationId;
    }
}
