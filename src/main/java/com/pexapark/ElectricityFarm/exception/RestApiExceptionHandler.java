package com.pexapark.ElectricityFarm.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice(annotations = RestController.class)
@Slf4j
public class RestApiExceptionHandler {

    @ExceptionHandler(ApiValidationException.class)
    public ResponseEntity<?> handleException(HttpServletRequest request, ApiValidationException ex) {
        log.error(ex.getMessage(), ex);

        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = ex.getMessage();

        String errorCode = ex.getValidationErrorCode().toString();

        RestApiExceptionHandler.ErrorResponse errorResponse =
                new RestApiExceptionHandler.ErrorResponse(status.value(), errorCode, message);

        return new ResponseEntity<>(errorResponse, status);
    }

    @AllArgsConstructor
    @Getter
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private static class ErrorResponse {
        private final Integer httpCode;
        private final String errorCode;
        private final String message;
    }
}
