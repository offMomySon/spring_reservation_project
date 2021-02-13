package kr.or.connect.reservation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.or.connect.reservation.model.ReservationInfo;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationCancleResult {

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

    public ReservationCancleResult(long reservationInfoId, long productId, long displayInfoId, String reservationName, String reservationTel, String reservationEmail, Date reservationDate, Boolean cancelFlag, Date createDate, Date modifyDate) {
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
    }

    public static ReservationCancleResult createReservationCancleResult(@Nonnull ReservationInfo reservationInfo) {
        ReservationCancleResult reservationCancleResult = new ReservationCancleResult(
                reservationInfo.getId(),
                reservationInfo.getProduct().getId(),
                reservationInfo.getDisplayInfo().getId(),
                reservationInfo.getReservationName(),
                reservationInfo.getReservationTel(),
                reservationInfo.getReservationEmail(),
                reservationInfo.getReservationDate(),
                reservationInfo.getCancelFlag(),
                reservationInfo.getCreateDate(),
                reservationInfo.getModifyDate()
        );
        return reservationCancleResult;
    }

    @Override
    public String toString() {
        return "ReservationCancleResult{" +
                "reservationInfoId=" + reservationInfoId +
                ", productId=" + productId +
                ", displayInfoId=" + displayInfoId +
                ", reservationName='" + reservationName + '\'' +
                ", reservationTel='" + reservationTel + '\'' +
                ", reservationEmail='" + reservationEmail + '\'' +
                ", reservationDate=" + reservationDate +
                ", cancelFlag=" + cancelFlag +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", prices=" + prices +
                '}';
    }
}
