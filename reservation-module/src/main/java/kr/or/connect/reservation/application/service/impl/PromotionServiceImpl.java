package kr.or.connect.reservation.application.service.impl;

import kr.or.connect.reservation.application.service.PromotionService;
import kr.or.connect.reservation.core.presentation.domain.ProductImage;
import kr.or.connect.reservation.core.presentation.domain.Promotion;
import kr.or.connect.reservation.infrastructure.repository.PromotionRepository;
import kr.or.connect.reservation.core.presentation.dto.PromotionResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
public class PromotionServiceImpl implements PromotionService {
    final private PromotionRepository promotionRepository;

    public PromotionServiceImpl(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @Nonnull
    @Override
    public List<PromotionResult> getPromotionList() {
        List<PromotionResult> promotionResults = new ArrayList();

        List<Object[]> promotionAndProductImages = promotionRepository.findByProductId(PageRequest.of(0, SELECT_PROMOTION_LIMIT ));

        for (Object[] objects : promotionAndProductImages) {
            Promotion promotion = (Promotion) objects[0];
            ProductImage productImage = (ProductImage) objects[1];

            promotionResults.add(PromotionResult.builder()
                    .id(promotion.getId())
                    .productId(productImage.getProduct().getId())
                    .productImageUrl(productImage.getFileInfo().getFileName()).build()
            );
        }

        return promotionResults;
    }
}
