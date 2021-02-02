package kr.or.connect.reservation.exception;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionReciever {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiErrorResponse> handleNullPointerException(Exception ex) {
		Optional<ExceptionGroup> optionalExceptionGroup = ExceptionGroup.find(ex.getClass());

		if (!optionalExceptionGroup.isPresent()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiErrorResponse("error-9999", "unknown error"));
		}

		ExceptionGroup exceptionGroup = optionalExceptionGroup.get();
		return ResponseEntity.status(exceptionGroup.getStatus())
				.body(new ApiErrorResponse(exceptionGroup.getError(), exceptionGroup.getMessage()));
	}
}
