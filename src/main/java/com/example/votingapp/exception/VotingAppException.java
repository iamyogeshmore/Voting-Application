package com.example.votingapp.exception;

public class VotingAppException extends RuntimeException {
    String message;

    public VotingAppException(String message) {
        this.message = message;
    }

    public VotingAppException() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
