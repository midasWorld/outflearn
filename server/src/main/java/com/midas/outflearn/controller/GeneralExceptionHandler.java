package com.midas.outflearn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.midas.outflearn.controller.ApiResponse.ERROR;

@ControllerAdvice
public class GeneralExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ResponseEntity<ApiResponse<?>> newResponse(Throwable throwable, HttpStatus status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(ERROR(throwable, status), headers, status);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<?> handleBadRequestException(Exception e) {
        logger.debug("Bad request exception occured: {}", e.getMessage(), e);
        return newResponse(e, HttpStatus.BAD_REQUEST);
    }
}
