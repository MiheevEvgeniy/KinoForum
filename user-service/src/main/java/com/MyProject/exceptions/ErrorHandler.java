package com.MyProject.exceptions;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({NotFoundException.class, EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(final Exception e) {
        return new ErrorResponse(HttpStatus.NOT_FOUND,
                LocalDateTime.now(),
                e.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class,
            HttpMessageNotReadableException.class,
            ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequestException(final Exception e) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST,
                LocalDateTime.now(),
                e.getMessage());
    }

    @Getter
    private static class ErrorResponse {
        String message;
        HttpStatus status;
        LocalDateTime timestamp;

        @Override
        public String toString() {
            return "ApiError{" +
                    "status=" + status +
                    ", timestamp=" + timestamp +
                    ", message='" + message + '\'' +
                    '}';
        }

        ErrorResponse(HttpStatus status,
                 LocalDateTime timestamp,
                 String message) {
            this.message = message;
            this.status = status;
            this.timestamp = timestamp;
        }
    }
}
