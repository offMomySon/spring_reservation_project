package kr.or.connect.reservation.service.impl;

import kr.or.connect.reservation.dto.PromotionResult;
import kr.or.connect.reservation.exception.list.RelatedEntityAbsentException;
import kr.or.connect.reservation.model.Product;
import kr.or.connect.reservation.model.ProductImage;
import kr.or.connect.reservation.model.Promotion;
import kr.or.connect.reservation.repository.ProductImageRepository;
import kr.or.connect.reservation.service.PromotionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
public class PromotionServiceImpl implements PromotionService {
    @Autowired
    private ProductImageRepository productImageRepository;

    @Nonnull
    @Override
    public List<PromotionResult> getPromotionList() {
        List<ProductImage> productImages = productImageRepository.findAllByTypeFetchProduct("th");

        List<PromotionResult> promotionResults = productImages.stream()
                .filter(productImage -> productImage.getProduct().getPromotions().size() > 0)
                .map(productImage -> {
                    Product product = productImage.getProduct();
                    Promotion promotion = product.getPromotions().stream().findFirst().orElseThrow(() -> {
                        throw new RelatedEntityAbsentException();
                    });
                    String saveFileName = productImage.getFileInfo().getSaveFileName();

                    return PromotionResult.makePromotionResult(promotion.getId(), product.getId(), saveFileName);
                })
                .collect(Collectors.toList());

        return promotionResults;
    }
}
