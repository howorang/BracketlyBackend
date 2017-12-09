package edu.bracketly.backend.model.seeder;

import edu.bracketly.backend.model.bracket.Bracket;
import edu.bracketly.backend.model.bracket.Player;
import edu.bracketly.backend.model.bracket.PlayerSlot;

import java.util.*;

public class RandomSeedingStrategy implements SeedingStrategy {
    @Override
    public void seed(Bracket bracket, Set<Player> players) {
        List<Player> playersRandom = new ArrayList<>(players);
        Collections.shuffle(playersRandom);
        Iterator<PlayerSlot> playerSlotIterator = bracket.getStartingPlayerSlotsInPlayingOrder().iterator();
        for (Player player : playersRandom) {
            playerSlotIterator.next().setPlayer(player);
        }
    }
}
