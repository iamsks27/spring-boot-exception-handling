package com.example.demo.exception;

import lombok.Data;

import java.util.Date;

@Data
class ExceptionResponse {

    private Date timestamp;

    private String message;

    private Integer statusCode;

    private String path;

    ExceptionResponse(final Date timestamp, final String message, final Integer statusCode, final String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.statusCode = statusCode;
        this.path = details;
    }
}
