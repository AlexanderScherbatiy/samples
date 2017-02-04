package datatype.tree;

import com.sun.tools.doclint.Entity;
import datatype.queue.LinkedQueueDataType;
import datatype.queue.QueueDataType;

import java.util.Iterator;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by alexsch on 2/3/2017.
 */

public class BinarySearchTree<E extends Comparable<E>> implements Iterable<E> {

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
    public Iterator<E> iterator() {

        final QueueDataType<Node<E>> queue = new LinkedQueueDataType<>();
        queue.enqueue(root);

        return new Iterator<E>() {

            @Override
            public boolean hasNext() {
                return !queue.isEmpty();
            }

            @Override
            public E next() {
                Node<E> elem = queue.dequeue();
                Node<E> left = elem.left;
                Node<E> right = elem.right;

                if (left != null) {
                    queue.enqueue(left);
                }

                if (right != null) {
                    queue.enqueue(right);
                }

                return elem.value;
            }
        };
    }

    public Stream<E> stream() {
        return StreamSupport.stream(spliterator(), false);
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
