package kr.or.connect.reservation.core.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Price {
    private Long reservationInfoPriceId;
    private Long reservationInfoId;
    private Long productPriceId;
    private Long count;

    public Price(Long reservationInfoPriceId, Long reservationInfoId, Long productPriceId, Long count) {
        this.reservationInfoPriceId = reservationInfoPriceId;
        this.reservationInfoId = reservationInfoId;
        this.productPriceId = productPriceId;
        this.count = count;
    }
}
