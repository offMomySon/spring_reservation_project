package kr.or.connect.reservation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.or.connect.reservation.model.ReservationInfo;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import java.util.Date;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationResponseResult {
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
    @JsonProperty("displayInfo")
    private DisplayInfoResult displayInfoResult;
    private Long totalPrice;

    public ReservationResponseResult(long reservationInfoId, long productId, long displayInfoId, String reservationName,
                                     String reservationTel, String reservationEmail, Date reservationDate, Boolean cancelFlag, Date createDate,
                                     Date modifyDate, DisplayInfoResult displayInfoResult, Long totalPrice) {
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
        this.displayInfoResult = displayInfoResult;
        this.totalPrice = totalPrice;
    }

    public static ReservationResponseResult makeReservationResponseResult(@Nonnull ReservationInfo reservationInfo, @Nonnull DisplayInfoResult displayInfoResult, @Nonnull Long totalPrice) {
        ReservationResponseResult reservationResponseResult = new ReservationResponseResult(
                reservationInfo.getId(),
                reservationInfo.getProduct().getId(),
                reservationInfo.getDisplayInfo().getId(),
                reservationInfo.getReservationName(),
                reservationInfo.getReservationTel(),
                reservationInfo.getReservationEmail(),
                reservationInfo.getReservationDate(),
                reservationInfo.getCancelFlag(),
                reservationInfo.getCreateDate(),
                reservationInfo.getModifyDate(),
                displayInfoResult,
                totalPrice
        );

        return reservationResponseResult;
    }
}
