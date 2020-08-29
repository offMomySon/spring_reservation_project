package kr.or.connect.reservation.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonExceptionHandler {
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ApiErrorResponse>  handleNullPointerException(NullPointerException ex){
		//error-0000 mean, NullPointerException
		ApiErrorResponse response = new ApiErrorResponse("error-0000", "SomeThing wrong, please send another request.");
	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
}
