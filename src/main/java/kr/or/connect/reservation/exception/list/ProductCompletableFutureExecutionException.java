package kr.or.connect.reservation.exception.list;

import kr.or.connect.reservation.exception.ErrorCode;

public class ProductCompletableFutureExecutionException extends ApiCommonException {
    public ProductCompletableFutureExecutionException() {
        super(ErrorCode.PRODUCT_COMPLETABLE_FUTURE_EXECUTION_EXCEPTION);
    }
}