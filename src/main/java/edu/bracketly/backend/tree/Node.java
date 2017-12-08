package edu.bracketly.backend.tree;

import java.util.Set;

public interface Node {
    public Set<? extends Node> getChildren();
    public Node getParent();
    public boolean isLeaf();
}
