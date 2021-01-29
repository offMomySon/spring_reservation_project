package kr.or.connect.reservation.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    NULL_POINT_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "error-0000", "NPException, SomeThing wrong."),

    //COMMON
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "error-0001", " Invalid Input Value"),
    PARAM_NOT_VALID(HttpStatus.BAD_REQUEST, "error-0002", "The parameter entered is invalid"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "C003", " Invalid Input Value"),

    //ID NOT EXIST
    DISPLAYINFO_ID_NOT_EXIST(HttpStatus.NOT_FOUND, "error-0004", "The DisplayInfo id is not exist."),
    RESERVATION_ID_NOT_EXIST(HttpStatus.BAD_REQUEST, "error-0005", "The Reservation id is not exist.");

    private HttpStatus status;
    private String error;
    private String message;

    private ErrorCode(HttpStatus status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

}
