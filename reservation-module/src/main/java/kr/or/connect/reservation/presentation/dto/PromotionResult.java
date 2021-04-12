package kr.or.connect.reservation.presentation.dto;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PromotionResult {
    private long id;
    private long productId;
    private String productImageUrl;

//    static public PromotionResult makePromotionResult(long id, long productId, String productImageUrl) {
//        PromotionResult promotionResult = new PromotionResult();
//        promotionResult.setId(id);
//        promotionResult.setProductId(productId);
//        promotionResult.setProductImageUrl(productImageUrl);
//
//        return promotionResult;
//    }
}
