package edu.bracketly.backend.exception;

public class TournamentHasAlreadyBeenStartedException extends RuntimeException {
    public TournamentHasAlreadyBeenStartedException(String message) {
        super(message);
    }
}
