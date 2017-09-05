package structural.decorator;

import org.junit.Test;

import static org.junit.Assert.*;

public class DecoratorPatternTest {


    @Test
    public void testDecoratorPattern() {
        BoundedQueue queue = new BoundedQueueImpl(3);
        queue.enque(3);
        assertEquals(3, queue.deque());

        BoundedQueue incQueue = new IncBoundedQueue(queue);
        incQueue.enque(3);
        assertEquals(4, queue.deque());

        BoundedQueue doubleQueue = new DoubleBoundedQueue(queue);
        doubleQueue.enque(3);
        assertEquals(6, doubleQueue.deque());


        BoundedQueue incDoubleQueue = new IncBoundedQueue(new DoubleBoundedQueue(queue));
        incDoubleQueue.enque(3);
        assertEquals(8, incDoubleQueue.deque());

        BoundedQueue doubleIncQueue = new DoubleBoundedQueue(new IncBoundedQueue(queue));
        doubleIncQueue.enque(3);
        assertEquals(7, doubleIncQueue.deque());
    }

    @Test
    public void testIntBoundedQueue() {

        BoundedQueue queue = new BoundedQueueImpl(3);
        assertTrue(queue.isEmpty());
        assertFalse(queue.isFull());

        queue.enque(1);
        assertFalse(queue.isEmpty());
        assertFalse(queue.isFull());
        assertEquals(1, queue.deque());

        queue.enque(2);
        assertEquals(2, queue.deque());

        queue.enque(1);
        queue.enque(2);
        queue.enque(3);
        assertFalse(queue.isEmpty());
        assertTrue(queue.isFull());

        assertEquals(1, queue.deque());
        assertFalse(queue.isEmpty());
        assertFalse(queue.isFull());

        assertEquals(2, queue.deque());
        assertFalse(queue.isEmpty());
        assertFalse(queue.isFull());

        assertEquals(3, queue.deque());
        assertTrue(queue.isEmpty());
        assertFalse(queue.isFull());
    }
}
