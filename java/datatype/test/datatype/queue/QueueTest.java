package datatype.queue;

/**
 * Created by alexsch.
 */
public class QueueTest {

    public static void main(String[] args) {

        QueueDataType<String> queue = new LinkedQueueDataType<>();

        if (!queue.isEmpty()) {
            throw new RuntimeException("Queue is not empty!");
        }

        String one = "One";
        queue.enqueue(one);

        if (queue.isEmpty()) {
            throw new RuntimeException("Queue is empty!");
        }

        String elem = queue.dequeue();

        if (!queue.isEmpty()) {
            throw new RuntimeException("Queue is not empty!");
        }

        if (!one.equals(elem)) {
            throw new RuntimeException("Wrong element!");
        }

        String elem1 = "1";
        String elem2 = "2";
        String elem3 = "3";

        queue.enqueue(elem1);
        queue.enqueue(elem2);
        queue.enqueue(elem3);

        if (!elem1.equals(queue.dequeue())) {
            throw new RuntimeException("Wrong element!");
        }

        if (!elem2.equals(queue.dequeue())) {
            throw new RuntimeException("Wrong element!");
        }

        if (!elem3.equals(queue.dequeue())) {
            throw new RuntimeException("Wrong element!");
        }

        if (!queue.isEmpty()) {
            throw new RuntimeException("Queue is not empty!");
        }

    }
}
