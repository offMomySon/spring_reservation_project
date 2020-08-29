package kr.or.connect.reservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import kr.or.connect.reservation.controller.ReservationApiController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class RsvExceptionHandler {
	@ExceptionHandler(RsvRqtPricesNotExistExceiption.class)
	public ResponseEntity<ApiErrorResponse> handleRsvRqtPricesNotExistException(RsvRqtPricesNotExistExceiption ex) {
		/*
		 * error-0002 mean, get Reservation request, but request doesn't have ticket
		 * price list
		 */
		log.debug("handleRsvRqtPricesNotExistException is called");

		ApiErrorResponse response = new ApiErrorResponse("error-0002",
				"Not exist price list in Reservation Request\n" + ex.getRs().toString());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(RsvIdNotExistExceiption.class)
	public ResponseEntity<ApiErrorResponse> handleRsvIdNotExistException(RsvIdNotExistExceiption ex) {
		/*
		 * error-0003 mean, Reservation Id not exist.
		 */
		log.debug("handleRsvIdNotExistException is called");

		ApiErrorResponse response = new ApiErrorResponse("error-0003",
				"Reservation Id not exist.\n" + "rsvId : " + ex.getRsvId());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
}
