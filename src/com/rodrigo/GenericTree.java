package com.rodrigo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rodrigo on 25/11/16.
 */
public class GenericTree<T> {

    private Node<T> root;

    public Node<T> getRoot() {
        return root;
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }

    public static class Node<T> {

        Node<T> parent;
        T element;
        List<Node<T>> subtrees;

        public Node(T element) {
            this.element = element;
            subtrees = new ArrayList<>();
        }

        public T getElement() {
            return element;
        }

        public Node<T> getParent() {
            return parent;
        }

        public void addSubtree(Node<T> child) {

            if (!subtrees.contains(child)) {
                subtrees.add(child);
                child.parent = this;
            }
        }

        public void removeSubtree(int position) {
            subtrees.remove(position);
        }

        public List<Node<T>> getSubtrees() {
            return subtrees;
        }

        public Node getSubtree(int position) {
            return subtrees.get(position);
        }

        public int getSubtreeSize() {
            return subtrees.size();
        }
    }
}
