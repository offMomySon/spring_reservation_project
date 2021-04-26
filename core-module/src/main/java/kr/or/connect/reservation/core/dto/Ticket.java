package kr.or.connect.reservation.core.dto;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ticket {
    private long price;
    private long count;
}
