package kr.or.connect.reservation.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RsvIdNotExistExceiption extends RuntimeException {
	private long rsvId;
}
