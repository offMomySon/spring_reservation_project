package kr.or.connect.reservation.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import kr.or.connect.reservation.dto.Price;
import kr.or.connect.reservation.objmapper.ReservationDateDeserializer;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ReservationRequest {

    private long displayInfoId;
    private List<Price> prices;
    private long productId;
    private String reservationEmail;
    private String reservationName;
    @JsonProperty("reservationTelephone")
    private String reservationTel;
    private Date reservationDate;

    public ReservationRequest() {
    }

    @JsonSetter("reservationYearMonthDay")
    @JsonDeserialize(using = ReservationDateDeserializer.class)
    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }
}
