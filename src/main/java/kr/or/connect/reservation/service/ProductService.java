package kr.or.connect.reservation.service;

import kr.or.connect.reservation.dto.ProductDisplayInfoResult;

public interface ProductService {
    public static final long SELECT_COUNT_LIMIT = 4;

    public long getProductCount(long categoryId);

    public ProductDisplayInfoResult getProductDisplayInfo(long categoryId, long startPageNum);
}
