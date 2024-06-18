package ru.marattim.accounts.controller;

import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.marattim.accounts.dto.ErrorsResponse;
import ru.marattim.accounts.exception.BusinessException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionProcessor {
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsResponse handleConstraintViolation(ConstraintViolationException e) {
        List<String> errors = new ArrayList<>();

        e.getConstraintViolations().forEach(cv -> errors.add(cv.getMessage()));

        return new ErrorsResponse(errors);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsResponse handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        return new ErrorsResponse(errors);
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsResponse handleBusinessException(BusinessException e) {
        return new ErrorsResponse(List.of(e.getMessage()));
    }
}
