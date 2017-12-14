package edu.bracketly.backend.tree;

import java.util.List;
import java.util.Set;

public interface Node {
    public List<? extends Node> getChildren();
    public Node getParent();
    public boolean isLeaf();
}
