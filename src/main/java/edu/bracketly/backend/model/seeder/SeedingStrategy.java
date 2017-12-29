package edu.bracketly.backend.model.seeder;

import edu.bracketly.backend.model.entity.bracket.Bracket;
import edu.bracketly.backend.model.entity.user.Player;

import java.util.Set;

public interface SeedingStrategy {
    void seed(Bracket bracket, Set<Player> players);
}
