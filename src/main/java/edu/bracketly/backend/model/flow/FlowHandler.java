package edu.bracketly.backend.model.flow;

import edu.bracketly.backend.model.entity.match.Match;

public interface FlowHandler {
    Match playNextMatch() throws BracketIsPlayedException;

    void markAsPlayed(Long matchId, int winningSeatNumber);

    BRACKET_STATUS getBracketStatus();
}
