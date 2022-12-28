package com.habin.marketboro_mileage_task.module.exception;

import com.habin.marketboro_mileage_task.common.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.NoSuchElementException;

import static com.habin.marketboro_mileage_task.module.exception.GetPrintStackTrace.GetException;
import static java.lang.System.lineSeparator;
import static java.util.stream.Collectors.joining;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<String>> handleException(HttpServletRequest request, HttpServletResponse response, Exception e) {
		// 인지할 수 없는 오류
		String URL = request.getRequestURL().toString();
		String exception = new StringBuilder()
				.append(URL)
				.append(lineSeparator())
				.append(GetException(e))
				.toString();


		log.error("알 수 없는 오류가 발생했습니다.");
		log.error(exception);

		return ApiResponse.fail(exception);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ApiResponse<String>> handleNoSuchElementException(HttpServletRequest request, HttpServletResponse response, NoSuchElementException e) {
		String URL = request.getRequestURL().toString();
		String exception = new StringBuilder()
				.append(URL)
				.append(lineSeparator())
				.append(GetException(e))
				.toString();

		log.error(e.getMessage());
		log.error(exception);

		return ApiResponse.badRequest(e.getMessage(), exception);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ApiResponse<String>> handleConstraintViolationException(HttpServletRequest request, HttpServletResponse response, ConstraintViolationException e) {
		String URL = request.getRequestURL().toString();
		String exception = new StringBuilder()
				.append(URL)
				.append(lineSeparator())
				.append(GetException(e))
				.toString();

		String message = e.getConstraintViolations().stream()
				.map(ConstraintViolation::getMessage)
				.filter(StringUtils::hasText)
				.collect(joining(lineSeparator(), "", ""));

		log.error(e.getMessage());
		log.error(exception);

		return ApiResponse.badRequest(message, exception);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<String>> handleMethodArgumentNotValidException(HttpServletRequest request, HttpServletResponse response, MethodArgumentNotValidException e) {
		String URL = request.getRequestURL().toString();
		String exception = new StringBuilder()
				.append(URL)
				.append(lineSeparator())
				.append(GetException(e))
				.toString();

		String message = e.getBindingResult().getAllErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage)
				.filter(StringUtils::hasText)
				.collect(joining(lineSeparator(), "", ""));

		log.error(e.getMessage());
		log.error(exception);

		return ApiResponse.badRequest(message, exception);
	}
}
