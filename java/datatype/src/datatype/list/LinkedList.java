package datatype.list;

import java.util.Iterator;

/**
 * Created by alexsch on 3/9/2017.
 */
public class LinkedList<E> implements List<E> {

    private Node<E> head;
    private Node<E> tail;

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public void addHead(E elem) {
        if (head == null) {
            head = tail = new Node<E>(elem);
        } else {
            head = new Node<E>(elem, head);
        }
    }

    @Override
    public void addTail(E elem) {
        if (tail == null) {
            head = tail = new Node<E>(elem);
        } else {
            tail.next = new Node<E>(elem);
            tail = tail.next;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            Node<E> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public E next() {
                E value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node next;

        public Node(T value) {
            this(value, null);
        }

        public Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
}
