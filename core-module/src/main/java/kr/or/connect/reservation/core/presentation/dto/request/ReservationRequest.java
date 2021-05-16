package kr.or.connect.reservation.core.presentation.dto.request;

import kr.or.connect.reservation.core.presentation.dto.Price;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationRequest {

    private long displayInfoId;
    private long productId;
    private List<Price> prices;
    private String reservationEmail;
    private String reservationName;
    private String reservationTelephone;
    private LocalDateTime reservationYearMonthDay;
}
