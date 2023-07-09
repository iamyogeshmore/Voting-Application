package com.example.votingapp.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = VotingAppException.class)
    public String handleBookStoreException(VotingAppException votingAppException) {
        return votingAppException.getMessage();
    }
}
