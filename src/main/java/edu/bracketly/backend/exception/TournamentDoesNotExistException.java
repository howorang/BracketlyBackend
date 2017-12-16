package edu.bracketly.backend.exception;

public class TournamentDoesNotExistException extends RuntimeException {
    public TournamentDoesNotExistException(String message) {
        super(message);
    }
}
