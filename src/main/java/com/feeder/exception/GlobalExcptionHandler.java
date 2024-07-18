package com.feeder.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.feeder.payload.ApiResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestControllerAdvice
public class GlobalExcptionHandler {

	@ExceptionHandler(ReasourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ReasourceNotFoundException ex)
	{
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message,false);
		
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
}
