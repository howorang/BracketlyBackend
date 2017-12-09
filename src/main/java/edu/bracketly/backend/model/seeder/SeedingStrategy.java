package edu.bracketly.backend.model.seeder;

import edu.bracketly.backend.BracketlyApplication;
import edu.bracketly.backend.model.bracket.Bracket;
import edu.bracketly.backend.model.bracket.Player;

import java.util.Set;

public interface SeedingStrategy {
    void seed(Bracket bracket, Set<Player> players);
}
