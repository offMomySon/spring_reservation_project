package kr.or.connect.reservation.model;

import kr.or.connect.reservation.dto.request.ReservationRequest;
import kr.or.connect.reservation.model.audite.ReservationBaseEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "reservation_info")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationInfo extends ReservationBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "display_info_id")
    private DisplayInfo displayInfo;

    @Column(name = "reservation_name")
    private String reservationName;

    @Column(name = "reservation_tel")
    private String reservationTel;

    @Column(name = "reservation_email")
    private String reservationEmail;

    @Column(name = "cancel_flag")
    private Boolean cancelFlag;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reservationInfo")
    private List<ReservationUserComment> reservationUserComments = new ArrayList();

    // cascade = persist, delete 를 전파한다.
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "reservationInfo")
    private List<ReservationInfoPrice> reservationInfoPrices = new ArrayList();

    public static ReservationInfo makeReservationInfo(
            @Nonnull Product product,
            @Nonnull DisplayInfo displayInfo,
            @Nonnull List<ReservationInfoPrice> reservationInfoPrices,
            @Nonnull ReservationRequest reservationRequest) {
        ReservationInfo reservationInfo = new ReservationInfo();

        reservationInfo.setProduct(product);
        reservationInfo.setDisplayInfo(displayInfo);
        reservationInfoPrices.stream()
                .forEach(reservationInfoPrice -> reservationInfo.addReservationInfoPrice(reservationInfoPrice));
        reservationInfo.setReservationName(reservationRequest.getReservationName());
        reservationInfo.setReservationTel(reservationRequest.getReservationTelephone());
        reservationInfo.setReservationEmail(reservationRequest.getReservationEmail());
        reservationInfo.setCancelFlag(false);

        return reservationInfo;
    }

    public void setDisplayInfo(@Nonnull DisplayInfo displayInfo) {
        this.displayInfo = displayInfo;
        displayInfo.getReservationInfos().add(this);
    }

    public void addReservationInfoPrice(@Nonnull ReservationInfoPrice reservationInfoPrice) {
        this.reservationInfoPrices.add(reservationInfoPrice);
        reservationInfoPrice.setReservationInfo(this);
    }
}
