package edu.bracketly.backend.model;

import edu.bracketly.backend.tree.Node;
import edu.bracketly.backend.tree.Traverser;
import edu.bracketly.backend.utlis.MathUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SingleEliminationBracket extends Bracket {
    protected SingleEliminationBracket(Set<Player> competitors) {
        super(competitors);
        int numberOfPlayers = competitors.size();
        int perfectBracketSize = MathUtils.nextGreaterPowerOfTwo(numberOfPlayers);
        int byes = perfectBracketSize - numberOfPlayers;
        bracketRoot = generatePerfectBracket(perfectBracketSize);
        setMatchNumbers();
        if (byes != 0) {
            cutOutByes(byes);
        }
    }

    private int getRelativePlayerSlotDistance(PlayerSlot one, PlayerSlot two) {
        int distance = 0;
        while (true) {
            distance++;
            if (one.getParent().equals(two)) {
                return distance;
            }
            one = one.getParent();
            two = two.getParent();
        }
    }

    private void setMatchNumbers() {
        new Traverser(bracketRoot, new Traverser.OnEnter() {
            int number = 0;
            @Override
            public void enter(Node node) {
                PlayerSlot playerSlot = (PlayerSlot)node;
                playerSlot.setNumber(++number);
            }
        }).reverseTraverse();
    }

    private void cutOutByes(int byes) {
        List<PlayerSlot> leaves = getLeaves();
        int leavesCount = leaves.size();
        int distance = leavesCount / byes;
        for (int i = 0; i < byes; i+=distance) {
            PlayerSlot playerSlot = leaves.get(i);
            playerSlot.getParent().getChildren().clear();
        }
    }

    private List<PlayerSlot> getLeaves() {
        List<PlayerSlot> leaves = new ArrayList<>();
        new Traverser(bracketRoot, node -> {
            PlayerSlot playerSlot = ((PlayerSlot) node);
            if (playerSlot.isLeaf()) {
                leaves.add(playerSlot);
            }
        }).traverse();
        return leaves;
    }
}
