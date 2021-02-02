package kr.or.connect.reservation.exception;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

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
