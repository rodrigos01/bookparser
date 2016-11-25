package com.rodrigo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rodrigo on 25/11/16.
 */
public class GenericTree<T> {

    private Node root;

    public void add(T element) {
        Node node = new Node(element);
        if (root == null) {
            root = node;
        } else {
            root.addSubtree(node);
        }
    }

    public class Node {

        Node parent;
        T element;
        List<Node> subtrees;

        public Node(T element) {
            this.element = element;
        }

        void addSubtree(Node child) {
            if (subtrees == null) {
                subtrees = new ArrayList<>();
            }

            if (!subtrees.contains(child)) {
                subtrees.add(child);
                child.parent = this;
            }
        }

        void removeSubtree(int position) {
            subtrees.remove(position);
        }

        Node getSubtree(int position) {
            return subtrees.get(position);
        }

        int getSubtreeSize() {
            return subtrees.size();
        }
    }
}
