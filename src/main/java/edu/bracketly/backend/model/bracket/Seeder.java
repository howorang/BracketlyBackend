package edu.bracketly.backend.model.bracket;

import edu.bracketly.backend.BracketlyApplication;

import java.util.Set;

public abstract class Seeder {
    protected Bracket bracket;
    protected Set<Player> players;

    public abstract void seed();
}
