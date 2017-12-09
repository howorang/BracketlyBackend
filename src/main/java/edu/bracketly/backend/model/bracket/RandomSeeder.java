package edu.bracketly.backend.model.bracket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class RandomSeeder extends Seeder {
    @Override
    public void seed() {
        List<Player> players = new ArrayList<>(super.players);
        Collections.shuffle(players);
        Iterator<PlayerSlot> playerSlotIterator = bracket.getStartingPlayerSlotsInPlayingOrder().iterator();
        for (Player player : players) {
            playerSlotIterator.next().setPlayer(player);
        }
    }
}
