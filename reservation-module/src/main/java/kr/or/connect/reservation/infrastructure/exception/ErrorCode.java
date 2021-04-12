package kr.or.connect.reservation.infrastructure.exception;

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
    PRODUCT_ID_NOT_EXIST(HttpStatus.BAD_REQUEST, "error-0008", "The Product id is not exist."),
    PRODUCT_PRICE_ID_NOT_EXIST(HttpStatus.BAD_REQUEST, "error-0009", "The Product_PRICE id is not exist."),

    //COMPLETABLEFUTURE EXCEPTION
    PRODUCT_COMPLETABLE_FUTURE_INTERRUPT_EXCEPTION(HttpStatus.BAD_REQUEST, "error-0010", "While making product api response, problem happen."),
    PRODUCT_COMPLETABLE_FUTURE_EXECUTION_EXCEPTION(HttpStatus.BAD_REQUEST, "error-0011", "While making product api response, problem happen.");

    private final HttpStatus status;
    private final String error;
    private final String message;

    ErrorCode(HttpStatus status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

}
