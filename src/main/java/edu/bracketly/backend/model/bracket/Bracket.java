package edu.bracketly.backend.model.bracket;

import edu.bracketly.backend.model.flow.FlowHandler;

import java.util.HashSet;
import java.util.List;

/**
 * Created by Piotr Borczyk on 06.11.2017.
 */

public abstract class Bracket {
    private int numberOfPlayers;
    protected int numberOfRounds;
    protected Seat bracketRoot;
    protected FlowHandler flowHandler;

    public FlowHandler getFlowHandler() {
        return flowHandler;
    }

    public void setFlowHandler(FlowHandler flowHandler) {
        this.flowHandler = flowHandler;
    }

    public Seat getBracketRoot() {
        return bracketRoot;
    }

    public void setBracketRoot(Seat bracketRoot) {
        this.bracketRoot = bracketRoot;
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    protected Bracket(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    protected Seat generatePerfectBracket(int bracketSize) {
        return generatePerfectBracket(bracketSize, 0, null);
    }

    private Seat generatePerfectBracket(int bracketSize, int depth, Seat parent) {
        Seat root =  new Seat();
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

    public abstract List<Seat> getStartingSeatsInPlayingOrder();
    public abstract int getDistance(Seat one, Seat two);
}