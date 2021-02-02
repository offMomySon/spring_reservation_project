package kr.or.connect.reservation.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import kr.or.connect.reservation.dto.Price;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationRequest {

    private long displayInfoId;
    private List<Price> prices;
    private long productId;
    private String reservationEmail;
    private String reservationName;
    private String reservationTelephone;
    @JsonFormat(pattern = "yyyy.MM.dd")
    private Date reservationYearMonthDay;
}
