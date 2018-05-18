package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@ControllerAdvice
public class DemoApplicationExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ExceptionResponse handleAllException(final Exception ex, final HttpServletResponse response, final HttpServletRequest request) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ExceptionResponse.builder().message(ex.getMessage()).statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).path(request.getRequestURI()).timestamp(new Date()).build();
    }

    @ExceptionHandler(value = InvalidInputException.class)
    @ResponseBody
    public ExceptionResponse handleInvalidInputException(final InvalidInputException exception, final HttpServletResponse response, final HttpServletRequest request) {
        response.setStatus(exception.getErrorCode());
        return ExceptionResponse.builder().timestamp(new Date()).path(request.getRequestURI()).statusCode(exception.getErrorCode()).message(exception.getErrorMsg()).build();
    }

    @ExceptionHandler(value = java.lang.NumberFormatException.class)
    @ResponseBody
    public ExceptionResponse handleNumberFormatException(final java.lang.NumberFormatException ex, final HttpServletResponse response, final HttpServletRequest request) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return ExceptionResponse.builder().timestamp(new Date()).message(ex.getMessage()).statusCode(HttpStatus.BAD_REQUEST.value()).path(request.getRequestURI()).build();
    }

    @Builder
    @Data
    @AllArgsConstructor
    private static class ExceptionResponse {
        private Date timestamp;

        private String message;

        private Integer statusCode;

        private String path;
    }
}
