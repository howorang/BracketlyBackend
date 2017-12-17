package edu.bracketly.backend.factory;

import edu.bracketly.backend.model.entity.bracket.Seat;
import edu.bracketly.backend.model.entity.bracket.SingleEliminationBracket;
import edu.bracketly.backend.model.flow.BRACKET_STATUS;
import edu.bracketly.backend.model.flow.SingleEliminationBracketFlowHandler;
import edu.bracketly.backend.tree.Node;
import edu.bracketly.backend.tree.Traverser;
import edu.bracketly.backend.utlis.MathUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static edu.bracketly.backend.utlis.BracketUtils.getLeaves;

@Component
public class BracketFactory {

    public SingleEliminationBracket singleEliminationBracket(int numberOfPlayers) {
        SingleEliminationBracket bracket = new SingleEliminationBracket();
        bracket.setNumberOfPlayers(numberOfPlayers);
        bracket.setBracketStatus(BRACKET_STATUS.LIVE);
        int perfectBracketSize = MathUtils.nextGreaterPowerOfTwo(numberOfPlayers);
        bracket.setNumberOfRounds((int) MathUtils.log2(perfectBracketSize));
        int byes = perfectBracketSize - numberOfPlayers;
        Seat bracketRoot = generatePerfectBracket(perfectBracketSize);
        bracket.setBracketRoot(bracketRoot);
        setSeatNumbers(bracketRoot);
        if (byes != 0) {
            cutOutByes(bracketRoot, byes);
        }
        SingleEliminationBracketFlowHandler flowHandler = new SingleEliminationBracketFlowHandler(bracket);
        flowHandler.init();
        bracket.setFlowHandler(flowHandler);
        return bracket;
    }

    private void setSeatNumbers(Seat bracketRoot) {
        new Traverser(bracketRoot, new Traverser.OnEnter() {
            int number = 0;

            @Override
            public void enter(Node node) {
                Seat seat = (Seat) node;
                seat.setNumber(++number);
            }
        }).reverseTraverse();
    }

    private void cutOutByes(Seat bracketRoot ,int byes) {
        List<Seat> leaves = getLeaves(bracketRoot);
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

    protected Seat generatePerfectBracket(int bracketSize) {
        return generatePerfectBracket(bracketSize, 0, null);
    }

    private Seat generatePerfectBracket(int bracketSize, int depth, Seat parent) {
        Seat root = new Seat();
        root.setDepth(depth);
        root.setChildren(new ArrayList<>());
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

