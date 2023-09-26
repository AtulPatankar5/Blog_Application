package com.project.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.blog.DTO.APIResponse;

//Get response in the POstman/swagger

@RestControllerAdvice
public class GlobalExceptionHandler {

	// when data is not found from database --> Get response in the POstman/swagger
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<APIResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
		String message = ex.getMessage();
		APIResponse apiResponse = new APIResponse(message, false);
		return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.NOT_FOUND);
	}

	// Get response in the POstman/swagger
	// for getting error for each element in DTO -> When the input is not as per
	// record
	// localhost:8080/api/users/543234
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex) {

		Map<String, String> resp = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {// get all the messages and store it in key and values
																	// pair
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			resp.put(fieldName, message);
		});
		return new ResponseEntity<Map<String, String>>(resp, HttpStatus.BAD_REQUEST);
	}
}
