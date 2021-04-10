package kr.or.connect.reservation.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
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

//    protected ReservationRequestResult(long reservationInfoId, long productId, long displayInfoId, String reservationName, String reservationTel, String reservationEmail, Date reservationDate, Date createDate, Date modifyDate, List<Price> prices, Boolean cancelFlag) {
//        this.reservationInfoId = reservationInfoId;
//        this.productId = productId;
//        this.displayInfoId = displayInfoId;
//        this.reservationName = reservationName;
//        this.reservationTel = reservationTel;
//        this.reservationEmail = reservationEmail;
//        this.reservationDate = reservationDate;
//        this.createDate = createDate;
//        this.modifyDate = modifyDate;
//        this.prices = prices;
//        this.cancelFlag = cancelFlag;
//    }
}
