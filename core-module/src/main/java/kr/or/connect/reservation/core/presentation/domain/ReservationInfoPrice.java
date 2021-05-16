package kr.or.connect.reservation.core.presentation.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reservation_info_price")
public class ReservationInfoPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "count")
    private long count;
    @ManyToOne
    @JoinColumn(name = "product_price_id")
    private ProductPrice productPrice;
    @ManyToOne
    @JoinColumn(name = "reservation_info_id")
    private ReservationInfo reservationInfo;

    public static ReservationInfoPrice createReservationInfoPrice(@Nonnull ProductPrice productPrice, long count) {
        ReservationInfoPrice reservationInfoPrice = new ReservationInfoPrice();
        reservationInfoPrice.setProductPrice(productPrice);
        reservationInfoPrice.setCount(count);

        return reservationInfoPrice;
    }
}
