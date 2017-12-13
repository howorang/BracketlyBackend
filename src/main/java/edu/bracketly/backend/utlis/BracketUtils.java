package edu.bracketly.backend.utlis;

import edu.bracketly.backend.model.entity.bracket.Seat;
import edu.bracketly.backend.tree.Traverser;

import java.util.ArrayList;
import java.util.List;

public class BracketUtils {


    public static int getDistance(Seat one, Seat two) {
        return getRelativePlayerSlotDistance(one, two);
    }

    private static int getRelativePlayerSlotDistance(Seat one, Seat two) {
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

    public static List<Seat> getLeaves(Seat bracketRoot) {
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
