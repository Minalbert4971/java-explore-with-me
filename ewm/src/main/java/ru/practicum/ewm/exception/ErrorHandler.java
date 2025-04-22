package ru.practicum.ewm.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(NotFoundException ex) {
        log.debug("Not found error: {}", ex.getMessage());
        return new ErrorResponse("ERROR", "Not found");
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, MissingServletRequestParameterException.class, ConditionException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleArgumentException(Exception ex) {
        log.debug("Validation error: {}", ex.getMessage());
        return new ErrorResponse("ERROR", "Wrong/Missing argument(s)");
    }

    @ExceptionHandler({ApproveRequestException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleArgumentException(ApproveRequestException ex) {
        log.debug("Unable to change status of request: {}", ex.getMessage());
        return new ErrorResponse("ERROR", "Approve status error");
    }

    @ExceptionHandler({ValidationException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleArgumentException(ValidationException ex) {
        log.debug("Validation error: {}", ex.getMessage());
        return new ErrorResponse("ERROR", "Wrong argument(s)");
    }

    @ExceptionHandler({AuthentificationException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleArgumentException(AuthentificationException ex) {
        log.debug("Authentification error: {}", ex.getMessage());
        return new ErrorResponse("ERROR", "Authentification error");
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleArgumentException(DataIntegrityViolationException ex) {
        log.debug("Database error: {}", ex.getMessage());
        return new ErrorResponse("ERROR", "Database error");
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleAnyException(Exception ex) {
        log.debug("Unexpected error: {}", ex.getMessage());
        return new ErrorResponse("ERROR", "Unexpected error");
    }

}
