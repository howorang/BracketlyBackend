package edu.bracketly.backend.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Piotr Borczyk on 06.11.2017.
 */

public abstract class Bracket {
    protected List<Player> competitors;

    protected Bracket(List<Player> competitors) {
        this.competitors = competitors;
    }

    protected Match generatePerfectBracket(int bracketSize) {
        if (bracketSize == 2) {
            return new Match();
        }
        Match root =  new Match();

        root.setChildren(new ArrayList<>());
        root.getChildren().add(generatePerfectBracket(bracketSize / 2));
        root.getChildren().add(generatePerfectBracket(bracketSize / 2));

        return root;
    }
}