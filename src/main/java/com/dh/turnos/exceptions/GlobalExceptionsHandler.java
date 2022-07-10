package com.dh.turnos.exceptions;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionsHandler {

    public static Logger logger = Logger.getLogger(GlobalExceptionsHandler.class);

    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity<String> handleAnyHttpException(ResponseStatusException exception) {
        logger.error(exception.getMessage());
        return ResponseEntity.status(exception.getStatus()).body(exception.getMessage());
    }
}
