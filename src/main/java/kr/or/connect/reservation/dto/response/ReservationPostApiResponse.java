package kr.or.connect.reservation.dto.response;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import kr.or.connect.reservation.dto.Price;
import kr.or.connect.reservation.dto.ReservationRequestResult;
import kr.or.connect.reservation.objmapper.ReservationDateDeserializer;
import kr.or.connect.reservation.objmapper.ReservationDateSerializer;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationPostApiResponse {
    @Min(value = 0)
    private long reservationInfoId;
    @Min(value = 0)
    private long productId;
    @Min(value = 0)
    private long displayInfoId;
    @NotNull
    private String reservationName;
    @NotNull
    @JsonProperty("reservationTelephone")
    private String reservationTel;
    @NotNull
    private String reservationEmail;
    @NotNull
    private Date reservationDate;
    @NotNull
    @JsonProperty("cancelYn")
    private Boolean cancelFlag;
    @NotNull
    private Date createDate;
    @NotNull
    private Date modifyDate;
    @NotNull
    private List<Price> prices;

    public ReservationPostApiResponse(long reservationInfoId, long productId, long displayInfoId, String reservationName,
                                      String reservationTel, String reservationEmail, Date reservationDate, Boolean cancelFlag, Date createDate,
                                      Date modifyDate, List<Price> prices) {
        super();
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
