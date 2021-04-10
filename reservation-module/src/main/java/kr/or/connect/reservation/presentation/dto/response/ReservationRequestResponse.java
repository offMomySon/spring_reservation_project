package kr.or.connect.reservation.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import kr.or.connect.reservation.core.dto.Price;
import kr.or.connect.reservation.core.dto.ReservationRequestResult;
import kr.or.connect.reservation.infrastructure.objmapper.ReservationDateDeserializer;
import kr.or.connect.reservation.infrastructure.objmapper.ReservationDateSerializer;

import javax.annotation.Nonnull;
import java.util.Date;
import java.util.List;

public class ReservationRequestResponse {
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

    protected ReservationRequestResponse(long reservationInfoId, long productId, long displayInfoId, String reservationName, String reservationTel, String reservationEmail, Date reservationDate, Boolean cancelFlag, Date createDate, Date modifyDate, List<Price> prices) {
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
    @JsonSerialize(using = ReservationDateSerializer.class)
    public Date getReservationDate() {
        return reservationDate;
    }

    @JsonSetter("reservationYearMonthDay")
    @JsonDeserialize(using = ReservationDateDeserializer.class)
    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }
}
