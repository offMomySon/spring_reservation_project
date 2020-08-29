package kr.or.connect.reservation.exception;

import kr.or.connect.reservation.dto.ReservationRequestRs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class RsvRqtPricesNotExistExceiption extends RuntimeException {
	private ReservationRequestRs rs;

	public RsvRqtPricesNotExistExceiption(ReservationRequestRs rs) {
		super();
		this.rs = rs;
	}
}
