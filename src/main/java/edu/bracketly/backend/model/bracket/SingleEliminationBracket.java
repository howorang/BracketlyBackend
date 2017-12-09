package edu.bracketly.backend.model.bracket;

import edu.bracketly.backend.tree.Node;
import edu.bracketly.backend.tree.Traverser;
import edu.bracketly.backend.utlis.MathUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SingleEliminationBracket extends Bracket {
    public SingleEliminationBracket(int numberOfPlayers) {
        super(numberOfPlayers);
        int perfectBracketSize = MathUtils.nextGreaterPowerOfTwo(numberOfPlayers);
        int byes = perfectBracketSize - numberOfPlayers;
        bracketRoot = generatePerfectBracket(perfectBracketSize);
        setMatchNumbers();
        if (byes != 0) {
            cutOutByes(byes);
        }
        List<PlayerSlot> leaves = getLeaves();
        System.out.printf("ds");
    }

    @Override
    List<PlayerSlot> getStartingPlayerSlotsInPlayingOrder() {
        return getLeaves();
    }

    @Override
    int getDistance(PlayerSlot one, PlayerSlot two) {
        return getRelativePlayerSlotDistance(one, two);
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

        int byesCut = 0;
        int i = 0;
        while (byesCut != byes) {
            PlayerSlot playerSlot = leaves.get(i);
            playerSlot.getParent().setLeaf(true);
            playerSlot.getParent().getChildren().clear();
            i += distance;
            byesCut++;
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
