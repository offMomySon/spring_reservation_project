package kr.or.connect.reservation.model;

import lombok.Data;

import javax.annotation.Nonnull;
import javax.persistence.*;

@Entity
@Data
@Table(name = "reservation_info_price")
public class ReservationInfoPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "reservation_info_id")
    private long reservationInfoId;
    @Column(name = "count")
    private long count;
    @ManyToOne
    @JoinColumn(name = "product_price_id")
    private ProductPrice productPrice;

    public ReservationInfoPrice() {
    }

    public ReservationInfoPrice(long reservationInfoId, long count, @Nonnull ProductPrice productPrice) {
        this.reservationInfoId = reservationInfoId;
        this.count = count;
        this.productPrice = productPrice;
    }

    public ReservationInfoPrice(long reservationInfoId, long count) {
        this.reservationInfoId = reservationInfoId;
        this.count = count;
    }

    public static ReservationInfoPrice createReservationInfoPrice(long reservationInfoId, long count, @Nonnull ProductPrice productPrice) {
        return new ReservationInfoPrice(reservationInfoId, count, productPrice);
    }

    @Nonnull
    public ProductPrice getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(@Nonnull ProductPrice productPrice) {
        this.productPrice = productPrice;
    }

}
