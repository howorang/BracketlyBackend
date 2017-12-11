package edu.bracketly.backend.model.flow;

import edu.bracketly.backend.model.bracket.Seat;
import edu.bracketly.backend.model.bracket.SingleEliminationBracket;
import edu.bracketly.backend.tree.Traverser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class SingleEliminationBracketFlowHandler implements FlowHandler {

    private SingleEliminationBracket bracket;
    private List<List<Match>> rounds = new ArrayList<>();

    public SingleEliminationBracketFlowHandler(SingleEliminationBracket bracket) {
        this.bracket = bracket;
        initRounds();
    }

    private void initRounds() {
        Map<Integer, List<Seat>> seatsByDepth = new HashMap<>();
        new Traverser(bracket.getBracketRoot(), node -> {
            Seat seat = (Seat) node;
            seatsByDepth.putIfAbsent(seat.getDepth(), new ArrayList<>());
            seatsByDepth.get(seat.getDepth()).add(seat);
        }).traverse();

        for (int i = 1; i <= bracket.getNumberOfRounds(); i++) {
            rounds.add(initRound(seatsByDepth.get(bracket.getNumberOfRounds() - i)));
        }
    }

    private List<Match> initRound(List<Seat> seats) {
        List<Match> matches = new ArrayList<>();
        for (Seat winnerSeat : seats) {
            Match match = new Match();
            match.setSeats(winnerSeat.getChildren());
            match.setWinnerSeat(winnerSeat);
            match.setId(getMatchId(winnerSeat, match.getSeats()));
            matches.add(match);
        }
        return matches;
    }

    private long getMatchId(Seat winnerSeat, Set<Seat> seats) {
        StringBuilder id = new StringBuilder();
        for (Seat seat : seats) {
            id.append(seat.getNumber());
        }
        id.append(winnerSeat.getNumber());
        return Long.parseLong(id.toString());
    }
    
    @Override
    public Match playNextMatch() throws BracketIsPlayedException {
        List<Match> currentRound = rounds.get(bracket.getCurrentRoundNumber() - 1);
        Optional<Match> matchOptional = currentRound.stream().filter(match -> match.getMatchStatus() == MATCH_STATUS.NOT_PLAYED).findFirst();
        if (matchOptional.isPresent()) {
            Match match = matchOptional.get();
            match.setMatchStatus(MATCH_STATUS.LIVE);
            return match;
        } else {
            if (currentRound.stream().anyMatch(match -> match.getMatchStatus() == MATCH_STATUS.LIVE)) {
                return null;
            } else {
                if (bracket.getBracketStatus() != BRACKET_STATUS.PLAYED) {
                    startNewRound();
                    return playNextMatch();
                } else throw new BracketIsPlayedException();
            }
        }
    }

    @Override
    public BRACKET_STATUS getBracketStatus() {
        return bracket.getBracketStatus();
    }

    @Override
    public void markAsPlayed(Long matchId, int winnigSeatNumber) {
        Optional<Match> matchOptional = getCurrentRound().stream().filter(match -> match.getId() == matchId).findFirst();
        if (matchOptional.isPresent()) {
            Match match = matchOptional.get();
            Optional<Seat> winningSeat = match.getSeats().stream().filter(seat -> seat.getNumber() == winnigSeatNumber).findFirst();
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
        return rounds.get(bracket.getCurrentRoundNumber() - 1);
    }
}
