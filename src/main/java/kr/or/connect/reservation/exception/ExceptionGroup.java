package kr.or.connect.reservation.exception;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ExceptionGroup {
//	ApiErrorResponse response = new ApiErrorResponse("error-0000", "SomeThing wrong, please send another request.");

	NULL_POINT_EXCEPTION(NullPointerException.class, HttpStatus.BAD_REQUEST, "error-0000",
			"NPException, SomeThing wrong."),
	RSV_RQ_PRICE_LIST_NOT_EXIST(RsvRqtPricesNotExistExceiption.class, HttpStatus.BAD_REQUEST, "error-0001",
			"Not exist price list in Reservation Request"),
	RESERVATION_ID_NOT_EXIST(RsvIdNotExistExceiption.class, HttpStatus.BAD_REQUEST, "error-0002",
			"Reservation Id not exist."),
	CATEGORY_ID_NOT_EXIST(CategoryIdNotExistExceiption.class, HttpStatus.BAD_REQUEST, "error-0003",
			"wrong CategoryId Entered"),
	DISPLAYINFO_ID_NOT_EXIST(DisplayInfoIdNotExistExceiption.class, HttpStatus.BAD_REQUEST, "error-0004",
			"wrong DisplayInfoId Entered");

	private Class<? extends Exception> clz;
	private HttpStatus status;
	private String error;
	private String message;

	private ExceptionGroup(Class<? extends Exception> clz, HttpStatus status, String error, String message) {
		this.clz = clz;
		this.status = status;
		this.error = error;
		this.message = message;
	}

	public static Optional<ExceptionGroup> find(Class<? extends Exception> clz) {
		return Arrays.stream(values()).filter(exception -> exception.clz.equals(clz)).findFirst();
	}

}
