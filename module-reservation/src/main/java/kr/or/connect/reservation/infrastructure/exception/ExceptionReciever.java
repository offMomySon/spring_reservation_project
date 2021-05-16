package kr.or.connect.reservation.infrastructure.exception;

import kr.or.connect.reservation.infrastructure.exception.list.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.concurrent.ExecutionException;

@ControllerAdvice
@Slf4j
public class ExceptionReciever {

    /**
     * javax.validation.Valid or @Validated 으로 binding error 발생시 발생한다.
     * HttpMessageConverter 에서 등록한 HttpMessageConverter binding 못할경우 발생
     * 주로 @RequestBody, @RequestPart 어노테이션에서 발생
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("handleMethodArgumentNotValidException", ex);
        ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;
        ApiErrorResponse response = new ApiErrorResponse(errorCode.getError(), errorCode.getMessage());
        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }

    @ExceptionHandler(BindException.class)
    protected ResponseEntity<ApiErrorResponse> handleBindException(BindException e) {
        log.error("handleBindException", e);
        ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;
        ApiErrorResponse response = new ApiErrorResponse(errorCode.getError(), errorCode.getMessage());
        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }

    /**
     * enum type 일치하지 않아 binding 못할 경우 발생
     * 주로 @RequestParam enum으로 binding 못했을 경우 발생
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ApiErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("handleMethodArgumentTypeMismatchException", e);
        ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;
        ApiErrorResponse response = new ApiErrorResponse(errorCode.getError(), errorCode.getMessage());
        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }

    /**
     * 지원하지 않은 HTTP method 호출 할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ApiErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException", e);
        ErrorCode errorCode = ErrorCode.METHOD_NOT_ALLOWED;
        ApiErrorResponse response = new ApiErrorResponse(errorCode.getError(), errorCode.getMessage());
        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }

    @ExceptionHandler(RelatedEntityAbsentException.class)
    public ResponseEntity<ApiErrorResponse> handleRelatedEntityAbsentException(RelatedEntityAbsentException ex) {
        log.debug("handleRelatedEntityAbsentException is called");
        ErrorCode errorCode = ex.getErrorCode();
        ApiErrorResponse response = new ApiErrorResponse(errorCode.getError(), errorCode.getMessage());
        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }

    @ExceptionHandler(DisplayInfoIdNotExistException.class)
    public ResponseEntity<ApiErrorResponse> handleDisplayInfoIdNotExistException(DisplayInfoIdNotExistException ex) {
        log.debug("handleDisplayInfoIdNotExistException is called");
        ErrorCode errorCode = ex.getErrorCode();
        ApiErrorResponse response = new ApiErrorResponse(errorCode.getError(),
                errorCode.getMessage() + "\nselected displayId = " + ex.getDisplayInfoId());
        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }

    @ExceptionHandler(ReservationIdNotExistException.class)
    public ResponseEntity<ApiErrorResponse> handleRsvIdNotExistException(ReservationIdNotExistException ex) {
        log.debug("handleRsvIdNotExistException is called");
        ErrorCode errorCode = ex.getErrorCode();
        ApiErrorResponse response = new ApiErrorResponse(errorCode.getError(),
                errorCode.getMessage() + "\nselected ReservationID = " + ex.getReservationId());
        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }

    @ExceptionHandler(ProductIdNotExistException.class)
    public ResponseEntity<ApiErrorResponse> handleProductIdNotExistException(ProductIdNotExistException ex) {
        log.debug("handleProductIdNotExistException is called");
        ErrorCode errorCode = ex.getErrorCode();
        ApiErrorResponse response = new ApiErrorResponse(errorCode.getError(),
                errorCode.getMessage() + "\nselected ProductID = " + ex.getProductId());
        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }

    @ExceptionHandler(ProductPriceIdNotExistException.class)
    public ResponseEntity<ApiErrorResponse> handleProductPriceIdNotExistException(ProductPriceIdNotExistException ex) {
        log.debug("handleProductPriceIdNotExistException is called");
        ErrorCode errorCode = ex.getErrorCode();
        ApiErrorResponse response = new ApiErrorResponse(errorCode.getError(),
                errorCode.getMessage() + "\nselected ProductPriceID = " + ex.getProductPriceId());
        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }

    @ExceptionHandler(ApiCommonException.class)
    public ResponseEntity<ApiErrorResponse> handleApiCommonException(ApiCommonException ex) {
        log.debug("handleApiCommonException is called");
        ErrorCode errorCode = ex.getErrorCode();
        ApiErrorResponse response = new ApiErrorResponse(errorCode.getError(), errorCode.getMessage());
        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }

    @ExceptionHandler(ExecutionException.class)
    protected ResponseEntity<ApiErrorResponse> handleExecutionException(ExecutionException ex) {
        log.error("handleMethodArgumentNotValidException", ex);
        ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;
        ApiErrorResponse response = new ApiErrorResponse(errorCode.getError(), errorCode.getMessage());
        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }

    @ExceptionHandler(InterruptedException.class)
    protected ResponseEntity<ApiErrorResponse> handleInterruptedExceptionException(InterruptedException ex) {
        log.error("handleMethodArgumentNotValidException", ex);
        ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;
        ApiErrorResponse response = new ApiErrorResponse(errorCode.getError(), errorCode.getMessage());
        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception ex) {
        log.debug("handleException is called");
        ApiErrorResponse response = new ApiErrorResponse("error-9999", "unknown error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
