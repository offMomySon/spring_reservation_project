package kr.or.connect.reservation.application.service.impl;

import kr.or.connect.reservation.presentation.dto.PromotionResult;
import kr.or.connect.reservation.domain.ProductImage;
import kr.or.connect.reservation.domain.Promotion;
import kr.or.connect.reservation.infrastructure.repository.ProductImageRepository;
import kr.or.connect.reservation.infrastructure.repository.PromotionRepository;
import kr.or.connect.reservation.application.service.PromotionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

import static kr.or.connect.reservation.presentation.dto.PromotionResult.makePromotionResult;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {
    final private ProductImageRepository productImageRepository;
    final private PromotionRepository promotionRepository;

    @Nonnull
    @Override
    public List<PromotionResult> getPromotionList() {
        List<PromotionResult> promotionResults = new ArrayList();

        List<ProductImage> productImages = productImageRepository.findByTypeLimit("th", PageRequest.of(0, SELECT_PRODUCT_LIMIT, Sort.by("id")));
        for (ProductImage productImage : productImages) {
            List<Promotion> promotions = promotionRepository.findByProductId(productImage.getProduct().getId(), PageRequest.of(0, SELECT_PROMOTION_LIMIT, Sort.by("id")));

            for (Promotion promotion : promotions) {
                promotionResults.add(makePromotionResult(promotion.getId(), productImage.getProduct().getId(), productImage.getFileInfo().getSaveFileName()));
            }
        }
        return promotionResults;
    }
}
