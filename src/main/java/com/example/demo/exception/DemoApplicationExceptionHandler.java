package com.example.demo.exception;

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
        return new ExceptionResponse(new Date(), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), request.getRequestURI());
    }

    @ExceptionHandler(value = InvalidInputException.class)
    @ResponseBody
    public ExceptionResponse handleInvalidInputException(final InvalidInputException exception, final HttpServletResponse response, final HttpServletRequest request) {
        response.setStatus(exception.getErrorCode());
        return new ExceptionResponse(new Date(), exception.getErrorMsg(), exception.getErrorCode(), request.getRequestURI());
    }
}
