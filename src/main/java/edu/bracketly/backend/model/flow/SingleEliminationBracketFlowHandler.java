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
    private int currentRoundNumber = 1;
    private int numberOfRounds;
    private List<List<Match>> rounds = new ArrayList<>();
    private BRACKET_STATUS bracket_status = BRACKET_STATUS.LIVE;

    public SingleEliminationBracketFlowHandler(SingleEliminationBracket bracket) {
        this.bracket = bracket;
        numberOfRounds = bracket.getNumberOfRounds();
        initRounds();
    }

    private void initRounds() {
        Map<Integer, List<Seat>> seatsByDepth = new HashMap<>();
        new Traverser(bracket.getBracketRoot(), node -> {
            Seat seat =(Seat)node;
            seatsByDepth.putIfAbsent(seat.getDepth(), new ArrayList<>());
            seatsByDepth.get(seat.getDepth()).add(seat);
        }).traverse();

        for (int i = 1; i <= numberOfRounds; i++) {
            rounds.add(initRound(i, seatsByDepth.get(numberOfRounds - i)));
        }
    }

    private List<Match> initRound(int roundNumber, List<Seat> seats) {
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

    private List<Seat> getSeatsByDepth(int depth) {
        List<Seat> seats = new ArrayList<>();
        new Traverser(bracket.getBracketRoot(), node -> {
            Seat seat =(Seat)node;
            if (seat.getDepth() == depth) {
                seats.add(seat);
            }
        }).traverse();
        return seats;
    }

    @Override
    public Match playNextMatch() throws BracketIsPlayedException {
        List<Match> currentRound = rounds.get(currentRoundNumber - 1);
        Optional<Match> matchOptional = currentRound.stream().filter(match -> match.getMatchStatus() == MATCH_STATUS.NOT_PLAYED).findFirst();
        if (matchOptional.isPresent()) {
            Match match = matchOptional.get();
            match.setMatchStatus(MATCH_STATUS.LIVE);
            return match;
        } else {
            if (currentRound.stream().anyMatch(match -> match.getMatchStatus() == MATCH_STATUS.LIVE)) {
                return null;
            } else {
                if (bracket_status != BRACKET_STATUS.PLAYED) {
                    startNewRound();
                    return playNextMatch();
                } else throw new BracketIsPlayedException();
            }
        }
    }

    @Override
    public BRACKET_STATUS getBracketStatus() {
        return bracket_status;
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
        if (currentRoundNumber == bracket.getNumberOfRounds()) {
            if (getCurrentRound().stream().noneMatch(match -> match.getMatchStatus() == MATCH_STATUS.LIVE && match.getMatchStatus() == MATCH_STATUS.NOT_PLAYED)) {
                bracket_status = BRACKET_STATUS.PLAYED;
            }
        }
    }

    private void startNewRound() {
        ++currentRoundNumber;
    }

    private List<Match> getCurrentRound() {
        return rounds.get(currentRoundNumber - 1);
    }
}
