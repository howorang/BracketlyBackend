package edu.bracketly.backend.exception;

public class BracketDoesNotExistsException extends RuntimeException {
    public BracketDoesNotExistsException(String message) {
        super(message);
    }
}
