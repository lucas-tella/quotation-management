package br.com.inatel.quotationmanagement.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.inatel.quotationmanagement.dto.ErrorDto;

@RestControllerAdvice
public class AppExceptionHandler {
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorDto> handleException(HttpMessageNotReadableException message){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body
				(new ErrorDto(400, "Invalid value format."));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDto> handleException(MethodArgumentNotValidException message){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body
				(new ErrorDto(400, "Invalid body format."));
	}
}
