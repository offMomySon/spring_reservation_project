package kr.or.connect.reservation.presentation.dto;

import kr.or.connect.reservation.domain.ReservationInfoPrice;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;

@Data
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

    public static Price makePrice(@Nonnull ReservationInfoPrice reservationInfoPrice) {
        Price price = new Price();
        price.setReservationInfoPriceId(reservationInfoPrice.getId());
        price.setReservationInfoId(reservationInfoPrice.getReservationInfo().getId());
        price.setProductPriceId(reservationInfoPrice.getProductPrice().getId());
        price.setCount(reservationInfoPrice.getCount());

        return price;
    }
}
