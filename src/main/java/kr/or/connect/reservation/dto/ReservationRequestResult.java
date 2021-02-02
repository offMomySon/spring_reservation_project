package kr.or.connect.reservation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.or.connect.reservation.dto.request.ReservationRequest;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationRequestResult {

    private long reservationInfoId;
    private long productId;
    private long displayInfoId;
    private String reservationName;
    @JsonProperty("reservationTelephone")
    private String reservationTel;
    private String reservationEmail;
    private Date reservationDate;
    @JsonProperty("cancelYn")
    private Boolean cancelFlag;
    private Date createDate;
    private Date modifyDate;
    private List<Price> prices;

    protected ReservationRequestResult(long productId, long displayInfoId, String reservationName, String reservationTel, String reservationEmail, Date reservationDate, Date createDate, Date modifyDate, List<Price> prices, Boolean cancelFlag) {
        this.productId = productId;
        this.displayInfoId = displayInfoId;
        this.reservationName = reservationName;
        this.reservationTel = reservationTel;
        this.reservationEmail = reservationEmail;
        this.reservationDate = reservationDate;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.prices = prices;
        this.cancelFlag = cancelFlag;
    }

    public static ReservationRequestResult createReservationRequestResult(@Nonnull ReservationRequest reservationRequest) {
        Date date = new Date();
        ReservationRequestResult reservationRequestResult = new ReservationRequestResult(
                reservationRequest.getProductId(),
                reservationRequest.getDisplayInfoId(),
                reservationRequest.getReservationName(),
                reservationRequest.getReservationTelephone(),
                reservationRequest.getReservationEmail(),
                date,
                date,
                date,
                reservationRequest.getPrices(),
                false
        );
        return reservationRequestResult;
    }
}
