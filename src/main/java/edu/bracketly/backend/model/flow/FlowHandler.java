package edu.bracketly.backend.model.flow;

public interface FlowHandler {
    Match playNextMatch() throws BracketIsPlayedException;

    void markAsPlayed(Long matchId, int winningSeatNumber);

    BRACKET_STATUS getBracketStatus();
}
