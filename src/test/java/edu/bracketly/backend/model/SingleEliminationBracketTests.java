package edu.bracketly.backend.model;

import edu.bracketly.backend.model.bracket.Player;
import edu.bracketly.backend.model.bracket.Seat;
import edu.bracketly.backend.model.bracket.SingleEliminationBracket;
import edu.bracketly.backend.model.flow.Match;
import edu.bracketly.backend.model.seeder.RandomSeedingStrategy;
import org.junit.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class SingleEliminationBracketTests {

    @Test
    public void playSimpleTournament() {
        SingleEliminationBracket bracket = new SingleEliminationBracket(4);
        RandomSeedingStrategy randomSeedingStrategy = new RandomSeedingStrategy();
        Set<Player> players = LongStream.range(1, 5).mapToObj(p -> new Player(p, "Name")).collect(Collectors.toSet());
        randomSeedingStrategy.seed(bracket, players);

        Match match = bracket.getFlowHandler().playNextMatch();
        Seat winnerSeat1 = match.getSeats().iterator().next();
        bracket.getFlowHandler().markAsPlayed(match.getId(), winnerSeat1.getNumber());

        Match match1 = bracket.getFlowHandler().playNextMatch();
        Seat winnerSeat2 = match1.getSeats().iterator().next();
        bracket.getFlowHandler().markAsPlayed(match1.getId(), winnerSeat2.getNumber());

        Match match2 = bracket.getFlowHandler().playNextMatch();
        Seat winnerSeat3 = match2.getSeats().iterator().next();
        bracket.getFlowHandler().markAsPlayed(match2.getId(), winnerSeat3.getNumber());

        Match match3 = bracket.getFlowHandler().playNextMatch();
        Seat winnerSeat4 = match3.getSeats().iterator().next();
        bracket.getFlowHandler().markAsPlayed(match3.getId(), winnerSeat4.getNumber());

        Match match4 = bracket.getFlowHandler().playNextMatch();
        Seat winnerSeat5 = match4.getSeats().iterator().next();
        bracket.getFlowHandler().markAsPlayed(match4.getId(), winnerSeat5.getNumber());



    }
}
