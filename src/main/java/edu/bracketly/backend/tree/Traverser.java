package edu.bracketly.backend.tree;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class Traverser {

    public interface OnEnter {
        void enter(Node node);
    }

    private Node root;
    private OnEnter onEnter;
    private Algorithim algorithim;

    public Traverser(Node root, OnEnter onEnter) {
        this.root = root;
        this.onEnter = onEnter;
        this.algorithim = new Bfs();
    }

    private interface Algorithim {
        void traverse(Node root);
        void reverseTraverse(Node root);
    }

    private class Bfs implements Algorithim {

        @Override
        public void traverse(Node root) {
            Queue<Node> queue = new ArrayDeque<>();
            queue.add(root);
            onEnter.enter(root);
            while (!queue.isEmpty()) {
                for (Node node : queue.remove().getChildren()) {
                    onEnter.enter(node);
                    queue.add(node);
                }
            }
        }

        @Override
        public void reverseTraverse(Node root) {
            Queue<Node> queue = new ArrayDeque<>();
            Stack<Node> stack = new Stack<>();
            queue.add(root);
            stack.add(root);
            while (!queue.isEmpty()) {
                for (Node node : queue.remove().getChildren()) {
                    queue.add(node);
                    stack.add(node);
                }
            }
            while (!stack.empty()) {
                onEnter.enter(stack.pop());
            }
        }
    }

    public void traverse() {
       algorithim.traverse(root);
    }

    public void reverseTraverse() {
        algorithim.reverseTraverse(root);
    }
}