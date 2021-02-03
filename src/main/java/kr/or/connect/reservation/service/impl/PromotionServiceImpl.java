package kr.or.connect.reservation.service.impl;

import kr.or.connect.reservation.dto.PromotionResult;
import kr.or.connect.reservation.model.Product;
import kr.or.connect.reservation.repository.PromotionRepository;
import kr.or.connect.reservation.service.PromotionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
public class PromotionServiceImpl implements PromotionService {
    @Autowired
    private PromotionRepository promotionRepository;

    @Nonnull
    @Override
    public List<PromotionResult> getPromotionList() {
        List<PromotionResult> promotionResults = new ArrayList();

        List<Product> products = promotionRepository.selectAll();
        for (Product product : products) {
            long promotionId = product.getPromotions().stream().findFirst().get().getId();
            long productId = product.getId();
            String saveFileName = product.getProductImages().stream().findFirst().get().getFileInfo().getSaveFileName();

            promotionResults.add(new PromotionResult(promotionId, productId, saveFileName));
        }
        return promotionResults;
    }
}
