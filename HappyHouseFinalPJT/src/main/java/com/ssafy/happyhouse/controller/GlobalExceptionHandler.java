package com.ssafy.happyhouse.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 개별 Controller Exception 이 처리하지 않는 Exception 일괄 처리
// 일반 요청 및 ajax 요청 모두를 처리하려면 ajax 요청을 구분할 수 있는 reqeuest header 등을 활용해서 처리를 구분해야 함.
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	// 브라우저 요청
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<String> defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		System.out.println("에러 발생!");
		String err = "Error";
		
		return new ResponseEntity<String>(err, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
