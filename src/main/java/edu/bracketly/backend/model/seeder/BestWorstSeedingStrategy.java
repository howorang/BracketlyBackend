package edu.bracketly.backend.model.seeder;

import edu.bracketly.backend.model.entity.bracket.Bracket;
import edu.bracketly.backend.model.entity.bracket.Seat;
import edu.bracketly.backend.model.entity.user.User;

import java.util.*;

public class BestWorstSeedingStrategy implements SeedingStrategy {
    @Override
    public void seed(Bracket bracket, Set<User> players) {
        List<User> playersSorted = new ArrayList<>();
        playersSorted.sort((o1, o2) -> (int) (o1.getDetails().getRank() - o2.getDetails().getRank()));
        Deque<User> playerDeque = new ArrayDeque<>(players);
        players.clear();
        while (!playerDeque.isEmpty()) {
            players.add(playerDeque.pollLast());
            if (!playerDeque.isEmpty())players.add(playerDeque.pollFirst());
        }

        Iterator<Seat> playerSlotIterator = bracket.getStartingSeatsInPlayingOrder().iterator();
        for (User player : players) {
            playerSlotIterator.next().setPlayer(player);
        }
    }
}
