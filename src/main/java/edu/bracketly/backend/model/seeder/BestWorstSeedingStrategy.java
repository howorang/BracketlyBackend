package edu.bracketly.backend.model.seeder;

import edu.bracketly.backend.model.bracket.Bracket;
import edu.bracketly.backend.model.bracket.Player;
import edu.bracketly.backend.model.bracket.Seat;

import java.util.*;

public class BestWorstSeedingStrategy implements SeedingStrategy {
    @Override
    public void seed(Bracket bracket, Set<Player> players) {
        List<Player> playersSorted = new ArrayList<>();
        playersSorted.sort((o1, o2) -> (int) (o1.getRank() - o2.getRank()));
        Deque<Player> playerDeque = new ArrayDeque<>(players);
        players.clear();
        while (!playerDeque.isEmpty()) {
            players.add(playerDeque.pollLast());
            if (!playerDeque.isEmpty())players.add(playerDeque.pollFirst());
        }

        Iterator<Seat> playerSlotIterator = bracket.getStartingPlayerSlotsInPlayingOrder().iterator();
        for (Player player : players) {
            playerSlotIterator.next().setPlayer(player);
        }
    }
}
