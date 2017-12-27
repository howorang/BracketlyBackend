package edu.bracketly.backend.exception;

public class RoundDoesNotExistException extends RuntimeException {
    public RoundDoesNotExistException(String message) {
        super(message);
    }
}
