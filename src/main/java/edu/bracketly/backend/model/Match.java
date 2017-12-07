package edu.bracketly.backend.model;

import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * Created by Piotr Borczyk on 06.11.2017.
 */

@Data
public class Match {
    private List<Player> players;
    private Player winner;
    private List<Match> children;
    private Math parent;

}
