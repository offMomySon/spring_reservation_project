package kr.or.connect.reservation.core.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.or.connect.reservation.core.presentation.domain.ReservationInfo;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
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
    private LocalDateTime reservationDate;
    @JsonProperty("cancelYn")
    private Boolean cancelFlag;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private List<Price> prices;

    public ReservationCancleResult(long reservationInfoId, long productId, long displayInfoId, String reservationName, String reservationTel, String reservationEmail, LocalDateTime reservationDate, Boolean cancelFlag, LocalDateTime createDate, LocalDateTime modifyDate) {
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

    public static ReservationCancleResult createDummyReservationCancleResult() {
        ReservationCancleResult reservationCancleResult = new ReservationCancleResult(
                0,
                0,
                0,
                "",
                "",
                "",
                null,
                null,
                null,
                null
        );
        return reservationCancleResult;
    }
}
