package com.example.Library.handler;

import com.example.Library.Exception.NotFoundException;
import com.example.Library.Exception.ValidationException;
import com.example.Library.response.BaseErrorResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<BaseErrorResponse> handleNotFoundExceptionException(@NonNull final NotFoundException exc) {
        log.error(exc.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new BaseErrorResponse(createErrorMessage(exc)));
    }

    private String createErrorMessage(Exception exception) {
        final String message = exception.getMessage();
        log.error(ExceptionHandlerUtils.buildErrorMessage(exception));
        return message;
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<BaseErrorResponse> handleValidationExceptionException(@NonNull final ValidationException exc) {
        log.error(exc.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new BaseErrorResponse(createErrorMessage(exc)));
    }
}