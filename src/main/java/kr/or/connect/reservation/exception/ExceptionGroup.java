package kr.or.connect.reservation.exception;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;

@Getter
public enum ExceptionGroup {
	NULL_POINT_EXCEPTION(NullPointerException.class, HttpStatus.INTERNAL_SERVER_ERROR, "error-0000",
			"NPException, SomeThing wrong."),
	RSV_RQ_PRICE_LIST_NOT_EXIST(RsvRqtPricesNotExistExceiption.class, HttpStatus.BAD_REQUEST, "error-0001",
			"The parameter entered is invalid. at postBook method"),
	CATEGORY_ID_NOT_EXIST(CategoryIdNotExistExceiption.class, HttpStatus.NOT_FOUND, "error-0003",
			"Unable to get result with entered categoryId."),
	DISPLAYINFO_ID_NOT_EXIST(DisplayInfoIdNotExistExceiption.class, HttpStatus.NOT_FOUND, "error-0004",
			"wrong DisplayInfoRs result");

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
