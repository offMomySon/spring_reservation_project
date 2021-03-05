package kr.or.connect.reservation.presentation.dto.response;

import kr.or.connect.reservation.presentation.dto.Price;
import kr.or.connect.reservation.presentation.dto.ReservationRequestResult;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationPostApiResponse {
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

    public ReservationPostApiResponse(long reservationInfoId, long productId, long displayInfoId, String reservationName,
                                      String reservationTel, String reservationEmail, Date reservationDate, Boolean cancelFlag, Date createDate,
                                      Date modifyDate, List<Price> prices) {
        this.reservationInfoId = reservationInfoId;
        this.productId = productId;
        this.displayInfoId = displayInfoId;
        this.reservationName = reservationName;
        this.reservationTelephone = reservationTel;
        this.reservationEmail = reservationEmail;
        this.reservationDate = reservationDate;
        this.cancelYn = cancelFlag;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.prices = prices;
    }

    public static ReservationPostApiResponse createReservationPostApiResponse(@Nonnull ReservationRequestResult reservationRequestResult) {
        ReservationPostApiResponse reservationPostApiResponse = new ReservationPostApiResponse(
                reservationRequestResult.getReservationInfoId(),
                reservationRequestResult.getProductId(),
                reservationRequestResult.getDisplayInfoId(),
                reservationRequestResult.getReservationName(),
                reservationRequestResult.getReservationTel(),
                reservationRequestResult.getReservationEmail(),
                reservationRequestResult.getReservationDate(),
                reservationRequestResult.getCancelFlag(),
                reservationRequestResult.getCreateDate(),
                reservationRequestResult.getModifyDate(),
                reservationRequestResult.getPrices()
        );
        return reservationPostApiResponse;
    }
}
