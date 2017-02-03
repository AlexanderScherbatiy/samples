package datatype.tree;

import java.util.Objects;

/**
 * Created by alexsch on 2/3/2017.
 */

public class BinarySearchTree<E extends Comparable<E>> {

    private Node root;

    public void insert(E value) {
        root = insert(root, value);
    }

    private Node<E> insert(Node<E> node, E value) {
        if (node == null) {
            return new Node<E>(value);
        }

        int compare = node.value.compareTo(value);

        if (compare > 0) {
            return new Node<E>(node.value, insert(node.left, value), node.right);
        } else if (compare < 0) {
            return new Node<E>(node.value, node.left, insert(node.right, value));
        } else {
            node.value = value;
            return node;
        }
    }

    @Override
    public String toString() {
        return Objects.toString(root);
    }

    public static class Node<V extends Comparable<V>> {
        private V value;
        private Node<V> left;
        private Node<V> right;

        public Node(V value) {
            this(value, null, null);
        }

        public Node(V value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            String l = left == null ? "" : left.toString();
            String r = right == null ? "" : right.toString();
            return String.format("(%s %s %s)", l, value, r);
        }
    }
}
