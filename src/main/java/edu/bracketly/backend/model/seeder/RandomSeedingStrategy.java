package edu.bracketly.backend.model.seeder;

import edu.bracketly.backend.model.entity.bracket.Bracket;
import edu.bracketly.backend.model.entity.user.User;
import edu.bracketly.backend.model.entity.bracket.Seat;

import java.util.*;

public class RandomSeedingStrategy implements SeedingStrategy {
    @Override
    public void seed(Bracket bracket, Set<User> players) {
        List<User> playersRandom = new ArrayList<>(players);
        Collections.shuffle(playersRandom);
        Iterator<Seat> playerSlotIterator = bracket.getStartingSeatsInPlayingOrder().iterator();
        for (User player : playersRandom) {
            playerSlotIterator.next().setPlayer(player);
        }
    }
}
