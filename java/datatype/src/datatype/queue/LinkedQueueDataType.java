package datatype.queue;

import java.util.NoSuchElementException;

/**
 * Created by alexsch.
 */
public class LinkedQueueDataType<T> implements QueueDataType<T> {


    private Node<T> head;
    private Node<T> tail;

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void enqueue(T elem) {
        if (isEmpty()) {
            head = tail = new Node<T>(elem);
        } else {
            tail.next = new Node<T>(elem);
            tail = tail.next;
        }
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty!");
        }

        T elem = head.elem;
        head = head.next;
        return elem;
    }

    private static class Node<T> {
        private final T elem;
        private Node<T> next;

        public Node(T elem) {
            this.elem = elem;
        }
    }
}
