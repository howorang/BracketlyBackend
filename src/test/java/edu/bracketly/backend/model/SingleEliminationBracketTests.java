package edu.bracketly.backend.model;

import org.junit.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SingleEliminationBracketTests {

    @Test
    public void testByeSpread() {
        Set<Player> players = IntStream.range(1,17).mapToObj(p -> new Player((long) p,"", false)).collect(Collectors.toSet());

        SingleEliminationBracket bracket = new SingleEliminationBracket(players);
    }
}
