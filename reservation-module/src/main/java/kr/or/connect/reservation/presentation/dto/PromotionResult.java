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
}
