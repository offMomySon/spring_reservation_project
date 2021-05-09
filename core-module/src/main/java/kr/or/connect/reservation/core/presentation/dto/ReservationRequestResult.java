package kr.or.connect.reservation.core.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
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
    private LocalDateTime reservationDate;
    @JsonProperty("cancelYn")
    private Boolean cancelFlag;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private List<Price> prices;
}
