package kr.or.connect.reservation.presentation.dto;

import kr.or.connect.reservation.domain.ProductImage;
import kr.or.connect.reservation.domain.Promotion;
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
