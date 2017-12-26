package edu.bracketly.backend.model.flow;

import edu.bracketly.backend.model.entity.match.Match;

import java.util.List;

public interface FlowHandler {
    Match getNextMatch() throws BracketIsPlayedException;

    void markAsPlayed(Long matchId, Long winningSeatNumber);

    BRACKET_STATUS getBracketStatus();

    List<Match> getAvailiableMatches();

    void startMatch(Long matchId);
}