package edu.bracketly.backend.model;

import edu.bracketly.backend.model.bracket.Player;
import edu.bracketly.backend.model.bracket.Seat;
import edu.bracketly.backend.model.bracket.SingleEliminationBracket;
import edu.bracketly.backend.model.flow.BRACKET_STATUS;
import edu.bracketly.backend.model.flow.BracketIsPlayedException;
import edu.bracketly.backend.model.flow.Match;
import edu.bracketly.backend.model.seeder.RandomSeedingStrategy;
import org.junit.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.junit.Assert.*;

public class SingleEliminationBracketTests {

    @Test
    public void newBracketStatus() {
        SingleEliminationBracket bracket = new SingleEliminationBracket(2);
        assertTrue(bracket.getFlowHandler().getBracketStatus() == BRACKET_STATUS.LIVE);
    }

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

        assertEquals(winnerSeat3.getPlayer(), bracket.getBracketRoot().getPlayer());
        assertTrue(bracket.getFlowHandler().getBracketStatus() == BRACKET_STATUS.PLAYED);

    }

    @Test(expected = BracketIsPlayedException.class)
    public void requestMatchOnPlayedBracket() {
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
        Seat winnerSeat4 = match2.getSeats().iterator().next();
        bracket.getFlowHandler().markAsPlayed(match2.getId(), winnerSeat4.getNumber());
    }
}
