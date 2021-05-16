package kr.or.connect.reservation.application.service;

import kr.or.connect.reservation.core.presentation.dto.response.ProductsApiAtDisplayInfoIdResponse;

public interface DisplayInfoService {
    public static final int SELECT_RESERVATION_INFO_COUNT_LIMIT = 10;
    public static final int SELECT_RESERVATION_USER_COMMENT_COUNT_LIMIT = 10;
    public static final int SELECT_PRODUCT_PRICE_COUNT_LIMIT = 10;
    public static final long SELECT_IMAGE_COUNT_LIMIT = 2;
    public static final int FIRST_PAGE = 0;

    public ProductsApiAtDisplayInfoIdResponse getDisplayInfo(long displayInfoId);
}
