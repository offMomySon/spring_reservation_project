package kr.or.connect.reservation.infrastructure.exception.list;

import kr.or.connect.reservation.infrastructure.exception.ErrorCode;
import lombok.Getter;

@Getter
public class DisplayInfoIdNotExistException extends ApiCommonException {
    private long displayInfoId;

    public DisplayInfoIdNotExistException(long displayInfoId) {
        super(ErrorCode.DISPLAYINFO_ID_NOT_EXIST);
        this.displayInfoId = displayInfoId;
    }
}
