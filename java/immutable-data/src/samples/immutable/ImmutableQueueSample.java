package samples.immutable;

import datatype.immutable.ImmutableQueue;

/**
 * Created by alexsch.
 */
public class ImmutableQueueSample {
    public static void main(String[] args) {
        ImmutableQueue<String> queue = ImmutableQueue.queueOf("One", "Two", "Three");
        System.out.printf("queue head: %s\n", queue.head());

        queue = new ImmutableQueue<>();
        queue = queue.enqueue("Test");
        System.out.printf("queue head: %s\n", queue.head());

    }
}
