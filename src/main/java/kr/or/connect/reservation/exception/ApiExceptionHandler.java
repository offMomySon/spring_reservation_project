package kr.or.connect.reservation.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ApiErrorResponse>  handleNullPointerException(NullPointerException ex){
		//error-0000 mean, NullPointerException
		ApiErrorResponse response = new ApiErrorResponse("error-0000", "SomeThing wrong, please send another request.");
	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiErrorResponse>  handleException(Exception ex){
		//error-0001 mean, Exception non expected error
		ApiErrorResponse response = new ApiErrorResponse("error-0001", "SomeThing wrong, please send another request.");
	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
}
