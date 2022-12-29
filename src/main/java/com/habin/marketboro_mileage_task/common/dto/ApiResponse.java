package com.habin.marketboro_mileage_task.common.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public record ApiResponse<T>(String message, T body) {

    private final static String SUCCESS_MESSAGE = "SUCCESS";
    private final static String CACHING_SUCCESS_MESSAGE = "CACHING SUCCESS";
    private final static String NOT_FOUND_MESSAGE = "NOT FOUND";
    private final static String FAILED_MESSAGE = "서버에서 오류가 발생하였습니다.";

    public static <T> ResponseEntity<ApiResponse<T>> success() {
        ApiResponse<T> apiResponse = new ApiResponse<>(SUCCESS_MESSAGE, null);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(T body) {
        ApiResponse<T> apiResponse = new ApiResponse<>(SUCCESS_MESSAGE, body);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    public static <T> ResponseEntity<ApiResponse<T>> cachingSuccess(T body) {
        ApiResponse<T> apiResponse = new ApiResponse<>(CACHING_SUCCESS_MESSAGE, body);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    public static <T> ResponseEntity<ApiResponse<T>> fail(T body) {
        ApiResponse<T> apiResponse = new ApiResponse<>(FAILED_MESSAGE, body);
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static <T> ResponseEntity<ApiResponse<T>> fail(String message, T body) {
        ApiResponse<T> apiResponse = new ApiResponse<>(message, body);
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static <T> ResponseEntity<ApiResponse<T>> badRequest(String message, T body) {
        ApiResponse<T> apiResponse = new ApiResponse<>(message, body);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

}
