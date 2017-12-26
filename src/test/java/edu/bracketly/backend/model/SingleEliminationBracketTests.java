package edu.bracketly.backend.model;

import edu.bracketly.backend.factory.BracketFactory;
import edu.bracketly.backend.model.entity.bracket.Seat;
import edu.bracketly.backend.model.entity.bracket.SingleEliminationBracket;
import edu.bracketly.backend.model.entity.match.Match;
import edu.bracketly.backend.model.entity.user.User;
import edu.bracketly.backend.model.flow.BRACKET_STATUS;
import edu.bracketly.backend.model.flow.BracketIsPlayedException;
import edu.bracketly.backend.model.seeder.RandomSeedingStrategy;
import org.junit.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SingleEliminationBracketTests {

    @Test
    public void newBracketStatus() {
        BracketFactory factory = new BracketFactory();
        SingleEliminationBracket bracket = factory.singleEliminationBracket(2);
        assertTrue(bracket.getFlowHandler().getBracketStatus() == BRACKET_STATUS.LIVE);
    }

    @Test
    public void playSimpleTournament() {
        BracketFactory factory = new BracketFactory();
        SingleEliminationBracket bracket = factory.singleEliminationBracket(4);
        RandomSeedingStrategy randomSeedingStrategy = new RandomSeedingStrategy();
        Set<User> players = LongStream.range(1, 5).mapToObj(p -> new User("Name", "Pass")).collect(Collectors.toSet());
        randomSeedingStrategy.seed(bracket, players);

        Match match = bracket.getFlowHandler().getNextMatch();
        Seat winnerSeat1 = match.getSeats().iterator().next();
        winnerSeat1.setId((long) winnerSeat1.getNumber());
        bracket.getFlowHandler().markAsPlayed(match.getId(), (long) winnerSeat1.getNumber());

        Match match1 = bracket.getFlowHandler().getNextMatch();
        Seat winnerSeat2 = match1.getSeats().iterator().next();
        winnerSeat2.setId((long) winnerSeat2.getNumber());
        bracket.getFlowHandler().markAsPlayed(match1.getId(), (long) winnerSeat2.getNumber());

        Match match2 = bracket.getFlowHandler().getNextMatch();
        Seat winnerSeat3 = match2.getSeats().iterator().next();
        winnerSeat3.setId((long) winnerSeat3.getNumber());
        bracket.getFlowHandler().markAsPlayed(match2.getId(), (long) winnerSeat3.getNumber());

        assertEquals(winnerSeat3.getPlayer(), bracket.getBracketRoot().getPlayer());
        assertTrue(bracket.getFlowHandler().getBracketStatus() == BRACKET_STATUS.PLAYED);

    }

    @Test(expected = BracketIsPlayedException.class)
    public void requestMatchOnPlayedBracket() {
        BracketFactory factory = new BracketFactory();
        SingleEliminationBracket bracket = factory.singleEliminationBracket(4);
        RandomSeedingStrategy randomSeedingStrategy = new RandomSeedingStrategy();
        Set<User> players = LongStream.range(1, 5).mapToObj(p -> new User("Name", "Pass")).collect(Collectors.toSet());
        randomSeedingStrategy.seed(bracket, players);

        Match match = bracket.getFlowHandler().getNextMatch();
        Seat winnerSeat1 = match.getSeats().iterator().next();
        winnerSeat1.setId((long) winnerSeat1.getNumber());
        bracket.getFlowHandler().markAsPlayed(match.getId(), (long) winnerSeat1.getNumber());

        Match match1 = bracket.getFlowHandler().getNextMatch();
        Seat winnerSeat2 = match1.getSeats().iterator().next();
        winnerSeat2.setId((long) winnerSeat2.getNumber());
        bracket.getFlowHandler().markAsPlayed(match1.getId(), (long) winnerSeat2.getNumber());

        Match match2 = bracket.getFlowHandler().getNextMatch();
        Seat winnerSeat3 = match2.getSeats().iterator().next();
        winnerSeat3.setId((long) winnerSeat3.getNumber());
        bracket.getFlowHandler().markAsPlayed(match2.getId(), (long) winnerSeat3.getNumber());

        Match match3 = bracket.getFlowHandler().getNextMatch();
        Seat winnerSeat4 = match3.getSeats().iterator().next();
        winnerSeat4.setId((long) winnerSeat4.getNumber());
        bracket.getFlowHandler().markAsPlayed(match2.getId(), (long) winnerSeat3.getNumber());
    }
}
