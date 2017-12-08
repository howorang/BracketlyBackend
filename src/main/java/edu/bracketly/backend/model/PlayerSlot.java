package edu.bracketly.backend.model;

import edu.bracketly.backend.tree.Node;
import lombok.*;

import java.util.Set;

/**
 * Created by Piotr Borczyk on 06.11.2017.
 */

@Getter
@Setter
@ToString(of = {"number", "depth"})
public class PlayerSlot implements Node {
    private Player player;
    private Set<PlayerSlot> children;
    private PlayerSlot parent;
    private boolean isLeaf;
    private int depth;
    private int number;
}