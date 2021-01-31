package kr.or.connect.reservation.exception.list;

import kr.or.connect.reservation.exception.ErrorCode;

public class ProductCompletableFutureInterruptedExceiption extends ApiCommonException {
    public ProductCompletableFutureInterruptedExceiption() {
        super(ErrorCode.PRODUCT_COMPLETABLE_FUTURE_INTERRUPT_EXCEPTION);
    }
}