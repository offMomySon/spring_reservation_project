package kr.or.connect.reservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import kr.or.connect.reservation.controller.ReservationApiController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ProductExceptionHandler {
	@ExceptionHandler(CategoryIdNotExistExceiption.class)
	public ResponseEntity<ApiErrorResponse> handleRsvRqtPricesNotExistException(CategoryIdNotExistExceiption ex) {
		/*
		 * error-0004 mean, wrong CategoryId.
		 */
		log.debug("handleRsvRqtPricesNotExistException is called");

		ApiErrorResponse response = new ApiErrorResponse("error-0004",
				"wrong CategoryId\nCategoryId : " + ex.getCategoryId());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(DisplayInfoIdNotExistExceiption.class)
	public ResponseEntity<ApiErrorResponse> handleRsvRqtPricesNotExistException(DisplayInfoIdNotExistExceiption ex) {
		/*
		 * error-0005 mean, wrong DisplayInfo id.
		 */
		log.debug("handleRsvRqtPricesNotExistException is called");

		ApiErrorResponse response = new ApiErrorResponse("error-0005",
				"wrong CategoryId\nCategoryId : " + ex.getDisplayInfoId());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
}
