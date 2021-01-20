package kr.or.connect.reservation.exception.list;

import kr.or.connect.reservation.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ReservationIdNotExistException extends ApiCommonException {
    private long reservationId;

    public ReservationIdNotExistException(long reservationId) {
        super(ErrorCode.DISPLAYINFO_ID_NOT_EXIST);
        this.reservationId = reservationId;
    }
}
