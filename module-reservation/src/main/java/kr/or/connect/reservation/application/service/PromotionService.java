package kr.or.connect.reservation.application.service;

import kr.or.connect.reservation.presentation.dto.PromotionResult;

import java.util.List;

public interface PromotionService {
    public static final int SELECT_PRODUCT_LIMIT = 50;
    public static final int SELECT_PROMOTION_LIMIT = 5;

    public List<PromotionResult> getPromotionList();
}
