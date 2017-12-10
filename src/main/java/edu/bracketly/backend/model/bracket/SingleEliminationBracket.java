package edu.bracketly.backend.model.bracket;

import edu.bracketly.backend.model.flow.SingleEliminationBracketFlowHandler;
import edu.bracketly.backend.tree.Node;
import edu.bracketly.backend.tree.Traverser;
import edu.bracketly.backend.utlis.MathUtils;

import java.util.ArrayList;
import java.util.List;

public class SingleEliminationBracket extends Bracket {
    public SingleEliminationBracket(int numberOfPlayers) {
        super(numberOfPlayers);
        int perfectBracketSize = MathUtils.nextGreaterPowerOfTwo(numberOfPlayers);
        numberOfRounds = (int) MathUtils.log2(numberOfPlayers);
        int byes = perfectBracketSize - numberOfPlayers;
        bracketRoot = generatePerfectBracket(perfectBracketSize);
        setMatchNumbers();
        if (byes != 0) {
            cutOutByes(byes);
        }
        flowHandler = new SingleEliminationBracketFlowHandler(this);
    }

    @Override
    public List<Seat> getStartingSeatsInPlayingOrder() {
        return getLeaves();
    }

    @Override
    public int getDistance(Seat one, Seat two) {
        return getRelativePlayerSlotDistance(one, two);
    }

    private int getRelativePlayerSlotDistance(Seat one, Seat two) {
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
                Seat seat = (Seat)node;
                seat.setNumber(++number);
            }
        }).reverseTraverse();
    }

    private void cutOutByes(int byes) {
        List<Seat> leaves = getLeaves();
        int leavesCount = leaves.size();
        int distance = leavesCount / byes;

        int byesCut = 0;
        int i = 0;
        while (byesCut != byes) {
            Seat seat = leaves.get(i);
            seat.getParent().setLeaf(true);
            seat.getParent().getChildren().clear();
            i += distance;
            byesCut++;
        }
    }

    private List<Seat> getLeaves() {
        List<Seat> leaves = new ArrayList<>();
        new Traverser(bracketRoot, node -> {
            Seat seat = ((Seat) node);
            if (seat.isLeaf()) {
                leaves.add(seat);
            }
        }).traverse();
        return leaves;
    }
}
