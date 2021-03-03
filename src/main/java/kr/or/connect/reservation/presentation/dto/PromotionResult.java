package kr.or.connect.reservation.presentation.dto;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PromotionResult {
    private long id;
    private long productId;
    private String productImageUrl;

    public PromotionResult(long id, long productId, String productImageUrl) {
        super();
        this.id = id;
        this.productId = productId;
        this.productImageUrl = productImageUrl;
    }

    static public PromotionResult makePromotionResult(long id, long productId, String productImageUrl) {
        PromotionResult promotionResult = new PromotionResult();
        promotionResult.setId(id);
        promotionResult.setProductId(productId);
        promotionResult.setProductImageUrl(productImageUrl);

        return promotionResult;
    }

}
