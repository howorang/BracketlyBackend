package edu.bracketly.backend.exception;

public class MatchDoesNotExistsException extends RuntimeException {
    public MatchDoesNotExistsException(String message) {
        super(message);
    }
}
