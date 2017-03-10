package datatype.tree;

import datatype.list.List;
import datatype.list.LinkedList;
import datatype.queue.QueueDataType;
import datatype.queue.LinkedQueueDataType;

import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by alexsch on 2/3/2017.
 */

public class RecursiveBinarySearchTree<E extends Comparable<E>> implements BinarySearchTree<E> {

    private Node root;

    @Override
    public boolean contains(E elem) {
        return contains(elem, root);
    }

    private boolean contains(E elem, Node<E> node) {

        return node == null ? false
                : node.value.equals(elem)
                || contains(elem, node.left)
                || contains(elem, node.right);
    }

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

        List<E> values = new LinkedList<E>();
        addNode(root, values);
        return values.iterator();
    }

    private void addNode(Node<E> node, List<E> list) {
        if (node != null) {
            addNode(node.left, list);
            list.addTail(node.value);
            addNode(node.right, list);
        }
    }

    public Stream<E> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    public Stream<E> parallelStream() {
        return StreamSupport.stream(new NodeSpliterator(root), true);
    }

    private static class NodeSpliterator<V extends Comparable<V>> implements Spliterator<V> {

        private final Node<V> node;
        private boolean traversed = false;
        private int split = 0;

        public NodeSpliterator(Node<V> node) {
            this.node = node;
        }

        @Override
        public boolean tryAdvance(Consumer<? super V> action) {
            if (action == null) {
                throw new NullPointerException();
            }

            if (node == null || traversed) {
                return false;
            }

            traversed = true;
            action.accept(node.value);
            return true;
        }

        @Override
        public Spliterator<V> trySplit() {
            if (node == null) {
                return null;
            }

            Node<V> splitNode = splitNode();
            return splitNode == null ? null : new NodeSpliterator<V>(splitNode);
        }

        private Node<V> splitNode() {
            if (split == 0) {
                split++;
                return node.left != null ? node.left : (node.right != null ? node.right : null);
            } else if (split == 1) {
                split++;
                return node.left != null && node.right != null ? node.right : null;
            }

            return null;
        }

        @Override
        public long estimateSize() {
            return getSize(node);
        }

        public int characteristics() {
            return (SIZED | SUBSIZED | CONCURRENT);
        }

        int getSize(Node<V> n) {
            return n == null ? 0 : 1 + getSize(n.left) + getSize(n.right);
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