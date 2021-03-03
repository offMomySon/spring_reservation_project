package kr.or.connect.reservation.infrastructure.exception.list;

import kr.or.connect.reservation.infrastructure.exception.ErrorCode;

public class CategoryIdNotExistException extends ApiCommonException {
    private long categoryId;

    public CategoryIdNotExistException(long categoryId) {
        super(ErrorCode.CATEGORY_ID_NOT_EXIST);
        this.categoryId = categoryId;
    }
}
