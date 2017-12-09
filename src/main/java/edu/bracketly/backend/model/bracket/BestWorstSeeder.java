package edu.bracketly.backend.model.bracket;

import java.util.*;

public class BestWorstSeeder extends Seeder {
    @Override
    public void seed() {
        List<Player> players = new ArrayList<>();
        players.sort((o1, o2) -> (int) (o1.getRank() - o2.getRank()));
        Deque<Player> playerDeque = new ArrayDeque<>(players);
        players.clear();
        while (!playerDeque.isEmpty()) {
            players.add(playerDeque.pollLast());
            if (!playerDeque.isEmpty())players.add(playerDeque.pollFirst());
        }

        Iterator<PlayerSlot> playerSlotIterator = bracket.getStartingPlayerSlotsInPlayingOrder().iterator();
        for (Player player : players) {
            playerSlotIterator.next().setPlayer(player);
        }
    }
}
