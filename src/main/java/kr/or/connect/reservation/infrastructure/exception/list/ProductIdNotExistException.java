package kr.or.connect.reservation.infrastructure.exception.list;

import kr.or.connect.reservation.infrastructure.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ProductIdNotExistException extends ApiCommonException {
    private long productId;

    public ProductIdNotExistException(long productId) {
        super(ErrorCode.PRODUCT_ID_NOT_EXIST);
        this.productId = productId;
    }
}

