package edu.bracketly.backend.model.flow;

import edu.bracketly.backend.model.entity.bracket.Bracket;
import edu.bracketly.backend.model.entity.bracket.Seat;
import edu.bracketly.backend.model.entity.bracket.SingleEliminationBracket;
import edu.bracketly.backend.model.entity.match.Match;
import edu.bracketly.backend.model.entity.match.Round;
import edu.bracketly.backend.tree.Traverser;

import java.util.*;

public class SingleEliminationBracketFlowHandler implements FlowHandler {

    private SingleEliminationBracket bracket;

    public SingleEliminationBracketFlowHandler(SingleEliminationBracket bracket) {
        this.bracket = bracket;
    }

    public void init() {
        initRounds(bracket);
    }

    private void initRounds(SingleEliminationBracket bracket) {
        Map<Integer, List<Seat>> seatsByDepth = new HashMap<>();
        new Traverser(bracket.getBracketRoot(), node -> {
            Seat seat = (Seat) node;
            seatsByDepth.putIfAbsent(seat.getDepth(), new ArrayList<>());
            seatsByDepth.get(seat.getDepth()).add(seat);
        }).traverse();

        for (int i = 1; i <= bracket.getNumberOfRounds(); i++) {
            bracket.getRounds().add(initRound(bracket, i, seatsByDepth.get(bracket.getNumberOfRounds() - i)));
        }
    }

    private Round initRound(Bracket bracket, int roundNumber, List<Seat> seats) {
        Round round = new Round();
        List<Match> matches = new ArrayList<>();
        for (Seat winnerSeat : seats) {
            Match match = new Match();
            match.setSeats(winnerSeat.getChildren());
            match.setWinnerSeat(winnerSeat);
            match.setTag(getMatchTag(winnerSeat, match.getSeats()));
            matches.add(match);
        }
        round.setMatches(matches);
        round.setBracket(bracket);
        round.setRoundNumber(roundNumber);
        return round;
    }

    private long getMatchTag(Seat winnerSeat, List<Seat> seats) {
        StringBuilder id = new StringBuilder();
        for (Seat seat : seats) {
            id.append(seat.getNumber());
        }
        id.append(winnerSeat.getNumber());
        return Long.parseLong(id.toString());
    }

    @Override
    public List<Match> getAvailiableMatches() {
        return getCurrentRound();
    }

    @Override
    public Match getNextMatch() throws BracketIsPlayedException {
        List<Match> currentRound = bracket.getRounds().get(bracket.getCurrentRoundNumber() - 1).getMatches();
        Optional<Match> matchOptional = currentRound.stream().filter(match -> match.getMatchStatus() == MATCH_STATUS.NOT_PLAYED).findFirst();
        if (matchOptional.isPresent()) {
            Match match = matchOptional.get();
            return match;
        } else {
            if (currentRound.stream().anyMatch(match -> match.getMatchStatus() == MATCH_STATUS.LIVE)) {
                return null;
            } else {
                if (bracket.getBracketStatus() != BRACKET_STATUS.PLAYED) {
                    startNewRound();
                    return getNextMatch();
                } else throw new BracketIsPlayedException();
            }
        }
    }

    @Override
    public BRACKET_STATUS getBracketStatus() {
        return bracket.getBracketStatus();
    }

    @Override
    public void markAsPlayed(Long matchId, Long winnigSeatNumber) {
        Optional<Match> matchOptional = getCurrentRound().stream().filter(match -> match.getId().equals(matchId)).findFirst();
        if (matchOptional.isPresent()) {
            Match match = matchOptional.get();
            Optional<Seat> winningSeat = match.getSeats().stream().filter(seat -> seat.getId().equals(winnigSeatNumber)).findFirst();
            match.getWinnerSeat().setPlayer(winningSeat.get().getPlayer());
            match.setMatchStatus(MATCH_STATUS.PLAYED);
        } else throw new RuntimeException("Match not found in current round");
        updateBracketStatusIfNeeded();
    }

    private void updateBracketStatusIfNeeded() {
        if (bracket.getCurrentRoundNumber() == bracket.getNumberOfRounds()) {
            if (getCurrentRound().stream().noneMatch(match -> match.getMatchStatus() == MATCH_STATUS.LIVE && match.getMatchStatus() == MATCH_STATUS.NOT_PLAYED)) {
                 bracket.setBracketStatus(BRACKET_STATUS.PLAYED);
            }
        }
    }

    private void startNewRound() {
        bracket.setCurrentRoundNumber(bracket.getCurrentRoundNumber() + 1);
    }

    private List<Match> getCurrentRound() {
        return bracket.getRounds().get(bracket.getCurrentRoundNumber() - 1).getMatches();
    }
}
