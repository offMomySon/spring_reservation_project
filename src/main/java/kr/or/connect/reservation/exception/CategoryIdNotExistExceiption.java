package kr.or.connect.reservation.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryIdNotExistExceiption extends RuntimeException {
	private long categoryId;
}
