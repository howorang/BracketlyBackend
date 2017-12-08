package edu.bracketly.backend.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Piotr Borczyk on 06.11.2017.
 */

public abstract class Bracket {
    protected Set<Player> competitors;
    PlayerSlot bracketRoot;

    protected Bracket(Set<Player> competitors) {
        this.competitors = competitors;
    }

    protected PlayerSlot generatePerfectBracket(int bracketSize) {
        return generatePerfectBracket(bracketSize, 0, null);
    }

    private PlayerSlot generatePerfectBracket(int bracketSize, int depth, PlayerSlot parent) {
        PlayerSlot root =  new PlayerSlot();
        root.setDepth(depth);
        root.setChildren(new HashSet<>());
        root.setParent(parent);
        if (bracketSize != 1) {
            int newDepth = ++depth;
            root.getChildren().add(generatePerfectBracket(bracketSize / 2, newDepth, root));
            root.getChildren().add(generatePerfectBracket(bracketSize / 2, newDepth, root));
        } else {
            root.setLeaf(true);
        }
        return root;
    }
}