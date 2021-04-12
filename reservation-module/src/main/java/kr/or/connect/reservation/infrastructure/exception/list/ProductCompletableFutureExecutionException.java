package kr.or.connect.reservation.infrastructure.exception.list;

import kr.or.connect.reservation.infrastructure.exception.ErrorCode;

public class ProductCompletableFutureExecutionException extends ApiCommonException {
    public ProductCompletableFutureExecutionException() {
        super(ErrorCode.PRODUCT_COMPLETABLE_FUTURE_EXECUTION_EXCEPTION);
    }
}