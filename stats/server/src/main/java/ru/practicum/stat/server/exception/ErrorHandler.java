package ru.practicum.stat.server.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class, MissingServletRequestParameterException.class, ConditionException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleArgumentException(Exception ex) {
        log.debug("Validation error: {}", ex.getMessage());
        return new ErrorResponse("ERROR", "Wrong/Missing argument(s)");
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleAnyException(Exception ex) {
        log.debug("Unexpected error: {}", ex.getMessage());
        return new ErrorResponse("ERROR", "Unexpected error");
    }

}
