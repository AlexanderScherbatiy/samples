package datatype.concurrent.executor;

/**
 * Created by alexsch on 4/26/2017.
 */
public class TimerExecutor {

    private Node accumulator;
    private Node unsorted;
    private Node sorted;
    private volatile boolean shutdown = false;

    private final Object lock = new Object();

    public TimerExecutor() {
        new Thread(new TimerThread()).start();
    }

    public void execute(long timeout, Runnable runnable) {

        synchronized (lock) {
            boolean empty = (accumulator == null);
            accumulator = new Node(getTime(timeout), runnable, accumulator);
            if (empty) {
                lock.notifyAll();
            }
        }
    }

    private static long getTime(long timeout) {
        return timeout + getTime();
    }

    private static long getTime() {
        return System.currentTimeMillis();
    }

    void insertTimeoutNode(Node node) {
        if (sorted == null) {
            sorted = node;
        } else if (sorted.time > node.time) {
            node.next = sorted;
            sorted = node;
        } else {

            Node curr = sorted;
            while (curr.next != null && curr.next.time < node.time) {
                curr = curr.next;
            }

            node.next = curr.next;
            curr.next = node;
        }
    }

    public void shutdown() {
        shutdown = true;
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    private class TimerThread implements Runnable {

        @Override
        public void run() {

            while (!shutdown) {

                synchronized (lock) {
                    while (true) {
                        if (accumulator == null) {
                            if (sorted == null) {
                                try {
                                    lock.wait();
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            } else {
                                long delta = sorted.time - getTime();
                                if (delta > 0) {
                                    try {
                                        lock.wait(delta);
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                } else {
                                    break;
                                }
                            }
                        } else {
                            break;
                        }
                    }

                    unsorted = accumulator;
                    accumulator = null;
                }

                while (unsorted != null) {
                    Node currNode = unsorted;
                    unsorted = unsorted.next;
                    currNode.next = null;
                    insertTimeoutNode(currNode);
                }

                if (sorted != null && sorted.time <= getTime()) {
                    sorted.runnable.run();
                    sorted = sorted.next;
                }
            }
        }
    }

    private static class Node {
        final long time;
        final Runnable runnable;
        Node next;

        public Node(long time, Runnable runnable, Node next) {
            this.time = time;
            this.runnable = runnable;
            this.next = next;
        }

        @Override
        public String toString() {
            return String.format("%d", (time / 1000) % 1000);
        }
    }
}
