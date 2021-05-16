package kr.or.connect.reservation.core.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import kr.or.connect.reservation.core.presentation.dto.Price;
import kr.or.connect.reservation.core.presentation.dto.ReservationRequestResult;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.List;

public class ReservationRequestResponse {
    private long reservationInfoId;
    private long productId;
    private long displayInfoId;
    private String reservationName;
    @JsonProperty("reservationTelephone")
    private String reservationTel;
    private String reservationEmail;
    private LocalDateTime reservationDate;
    @JsonProperty("cancelYn")
    private Boolean cancelFlag;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private List<Price> prices;

    protected ReservationRequestResponse(long reservationInfoId, long productId, long displayInfoId, String reservationName, String reservationTel, String reservationEmail, LocalDateTime reservationDate, Boolean cancelFlag, LocalDateTime createDate, LocalDateTime modifyDate, List<Price> prices) {
        this.reservationInfoId = reservationInfoId;
        this.productId = productId;
        this.displayInfoId = displayInfoId;
        this.reservationName = reservationName;
        this.reservationTel = reservationTel;
        this.reservationEmail = reservationEmail;
        this.reservationDate = reservationDate;
        this.cancelFlag = cancelFlag;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.prices = prices;
    }

    public static ReservationRequestResponse createReservationRequestResponse(@Nonnull ReservationRequestResult reservationRequestResult, @Nonnull List<Price> prices) {
        ReservationRequestResponse reservationRequestResponse = new ReservationRequestResponse(
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
                prices
        );
        return reservationRequestResponse;
    }

    @JsonGetter("reservationDate")
    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    @JsonSetter("reservationYearMonthDay")
    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }
}
