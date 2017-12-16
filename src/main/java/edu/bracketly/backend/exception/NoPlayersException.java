package edu.bracketly.backend.exception;

public class NoPlayersException extends RuntimeException {
    public NoPlayersException(String message) {
        super(message);
    }
}
