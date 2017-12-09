package edu.bracketly.backend.model.bracket;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Piotr Borczyk on 06.11.2017.
 */

abstract class Bracket {
    private int numberOfPlayers;
    PlayerSlot bracketRoot;

    protected Bracket(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
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

    abstract List<PlayerSlot> getStartingPlayerSlotsInPlayingOrder();
    abstract int getDistance(PlayerSlot one, PlayerSlot two);
}