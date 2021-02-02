package kr.or.connect.reservation.dto.response;

import kr.or.connect.reservation.dto.Price;
import kr.or.connect.reservation.dto.ReservationCancleResult;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationCancleResponse {
    private long reservationInfoId;
    private long productId;
    private long displayInfoId;
    private String reservationName;
    private String reservationTelephone;
    private String reservationEmail;
    private Date reservationDate;
    private Boolean cancelYn;
    private Date createDate;
    private Date modifyDate;
    private List<Price> prices;

    protected ReservationCancleResponse(long reservationInfoId, long productId, long displayInfoId, String reservationName, String reservationTel, String reservationEmail, Date reservationDate, Boolean cancelYn, Date createDate, Date modifyDate, List<Price> prices) {
        this.reservationInfoId = reservationInfoId;
        this.productId = productId;
        this.displayInfoId = displayInfoId;
        this.reservationName = reservationName;
        this.reservationTelephone = reservationTel;
        this.reservationEmail = reservationEmail;
        this.reservationDate = reservationDate;
        this.cancelYn = cancelYn;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.prices = prices;
    }

    public static ReservationCancleResponse createReservationCancleResponse(@Nonnull ReservationCancleResult reservationCancleResult, @Nonnull List<Price> prices) {
        ReservationCancleResponse reservationCancleResponse = new ReservationCancleResponse(
                reservationCancleResult.getReservationInfoId(),
                reservationCancleResult.getProductId(),
                reservationCancleResult.getDisplayInfoId(),
                reservationCancleResult.getReservationName(),
                reservationCancleResult.getReservationTel(),
                reservationCancleResult.getReservationEmail(),
                reservationCancleResult.getReservationDate(),
                reservationCancleResult.getCancelFlag(),
                reservationCancleResult.getCreateDate(),
                reservationCancleResult.getModifyDate(),
                prices
        );
        return reservationCancleResponse;
    }
}
