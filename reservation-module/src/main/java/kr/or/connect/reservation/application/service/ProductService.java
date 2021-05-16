package kr.or.connect.reservation.application.service;

import kr.or.connect.reservation.core.presentation.dto.ProductDisplayInfoResult;

public interface ProductService {
    public static final long SELECT_COUNT_LIMIT = 4;

    public ProductDisplayInfoResult getProductDisplayInfo(long categoryId, long startPageNum);
}
