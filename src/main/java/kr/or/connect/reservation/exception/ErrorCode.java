package kr.or.connect.reservation.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    NULL_POINT_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "error-0000", "NPException, SomeThing wrong."),

    //COMMON
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "error-0001", " Invalid Input Value"),
    PARAM_NOT_VALID(HttpStatus.BAD_REQUEST, "error-0002", "The parameter entered is invalid"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "error-0003", " Invalid Input Value"),
    RELATED_ENTITY_ABSENT(HttpStatus.BAD_REQUEST, "error-0004", "Related Entity absent."),

    //ID NOT EXIST
    DISPLAYINFO_ID_NOT_EXIST(HttpStatus.NOT_FOUND, "error-0005", "The DisplayInfo id is not exist."),
    RESERVATION_ID_NOT_EXIST(HttpStatus.BAD_REQUEST, "error-0006", "The Reservation id is not exist."),
    CATEGORY_ID_NOT_EXIST(HttpStatus.BAD_REQUEST, "error-0007", "The Category id is not exist."),

    //COMPLETABLEFUTURE EXCEPTION
    PRODUCT_COMPLETABLE_FUTURE_INTERRUPT_EXCEPTION(HttpStatus.BAD_REQUEST, "error-0008", "While making product api response, problem happen."),
    PRODUCT_COMPLETABLE_FUTURE_EXECUTION_EXCEPTION(HttpStatus.BAD_REQUEST, "error-0009", "While making product api response, problem happen.");

    private HttpStatus status;
    private String error;
    private String message;

    private ErrorCode(HttpStatus status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

}
