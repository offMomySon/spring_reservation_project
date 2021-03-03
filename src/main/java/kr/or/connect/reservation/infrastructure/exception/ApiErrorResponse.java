package kr.or.connect.reservation.infrastructure.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ApiErrorResponse {

    private Date timestamp;
    private String error;
    private String message;

    public ApiErrorResponse(String error, String message) {
        super();
        this.error = error;
        this.message = message;
        this.timestamp = new Date();
    }
}
