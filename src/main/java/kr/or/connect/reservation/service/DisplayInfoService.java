package kr.or.connect.reservation.service;

import kr.or.connect.reservation.dto.response.ProductsApiAtDisplayInfoIdResponse;

public interface DisplayInfoService {
    public static final long SELECT_IMAGE_COUNT_LIMIT = 2;
    public static final int FIRST_PAGE = 0;

    public ProductsApiAtDisplayInfoIdResponse getDisplayInfo(long displayInfoId);
}
