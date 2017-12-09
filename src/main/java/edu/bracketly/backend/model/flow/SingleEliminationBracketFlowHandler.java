package edu.bracketly.backend.model.flow;

import edu.bracketly.backend.model.bracket.Seat;
import edu.bracketly.backend.model.bracket.SingleEliminationBracket;
import edu.bracketly.backend.tree.Traverser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SingleEliminationBracketFlowHandler implements FlowHandler {

    private SingleEliminationBracket bracket;
    private int currentRoundNumber;
    private int numberOfRounds;
    private List<List<Match>> rounds;

    public SingleEliminationBracketFlowHandler(SingleEliminationBracket bracket) {
        this.bracket = bracket;
        rounds = new ArrayList<>();
        currentRoundNumber = 1;
        numberOfRounds = bracket.getNumberOfRounds();
        rounds.add(initRound(currentRoundNumber));
    }

    private List<Match> initRound(int roundNumber) {
        List<Seat> winnerSeats = getSeatsByDepth(numberOfRounds - roundNumber);
        List<Match> matches = new ArrayList<>();
        for (Seat winnerSeat : winnerSeats) {
            Match match = new Match();
            match.setSeats(winnerSeat.getChildren());
        }
        return matches;
    }

    private List<Seat> getSeatsByDepth(int depth) {
        List<Seat> seats = new ArrayList<>();
        new Traverser(bracket.getBracketRoot(), node -> {
            Seat seat =(Seat)node;
            if (seat.getDepth() == depth) {
                seats.add(seat);
            }
        });
        return seats;
    }

    @Override
    public Match playNextMatch() {
        List<Match> currentRound = rounds.get(currentRoundNumber - 1);
        Optional<Match> matchOptional = currentRound.stream().filter(match -> match.getMatchStatus() == MATCH_STATUS.NOT_PLAYED).findFirst();
        if (matchOptional.isPresent()) {
            Match match = matchOptional.get();
            match.setMatchStatus(MATCH_STATUS.LIVE);
            return match;
        } else {
            Optional<Match> anyLiveMatch = currentRound.stream().filter(match -> match.getMatchStatus() == MATCH_STATUS.LIVE).findAny();
            if (anyLiveMatch.isPresent()) {
                return null;
            } else {
                startNewRound();
                return playNextMatch();
            }
        }
    }

    public void markAsPlayes(Match match) {
        Match matchManaged = getCurrentRound().get(getCurrentRound().lastIndexOf(match));
        matchManaged.setMatchStatus(MATCH_STATUS.PLAYED);
    }

    private void startNewRound() {
        rounds.add(initRound(++currentRoundNumber));
    }

    private List<Match> getCurrentRound() {
        return rounds.get(currentRoundNumber - 1);
    }
}
