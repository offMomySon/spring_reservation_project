package kr.or.connect.reservation.core.dto;

import kr.or.connect.reservation.core.domain.ProductImage;
import kr.or.connect.reservation.core.domain.Promotion;
import lombok.Data;

@Data
public class PromotionAndProductImage {
    private Promotion promotion;
    private ProductImage productImage;

    public PromotionAndProductImage(Promotion promotion, ProductImage productImage) {
        this.promotion = promotion;
        this.productImage = productImage;
    }
}
