package kr.or.connect.reservation.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DisplayInfoIdNotExistExceiption extends RuntimeException {
	private long displayInfoId;
}
