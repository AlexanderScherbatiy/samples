package datatype.concurrent.actor;

/**
 * Created by alexsch on 5/8/2017.
 */
public class SynchronizedActorFactory implements ActorFactory {

    @Override
    public Actor createActor(MessageHandler messageHandler) {
        return new SynchronizedActor(messageHandler);
    }

    private static class SynchronizedActor implements Actor {

        private final Object lock = new Object();
        private final List<MessageItem> accumulator = new List();
        private final List<MessageItem> mailbox = new List();
        private final MessageHandler handler;

        public SynchronizedActor(MessageHandler handler) {
            this.handler = handler;

            new Thread(() -> {

                while (true) {
                    synchronized (lock) {
                        mailbox.append(accumulator);
                    }

                    while (!mailbox.isEmpty()) {
                        MessageItem item = mailbox.remove();
                        handler.handleMessage(item.message, item.sender);
                    }
                }
            }).start();
        }

        @Override
        public void sendMessage(Object message, Actor sender) {

            synchronized (lock) {
                accumulator.add(new MessageItem(message, sender));
            }
        }

        private static class MessageItem {
            final Object message;
            final Actor sender;

            public MessageItem(Object message, Actor sender) {
                this.message = message;
                this.sender = sender;
            }
        }
    }

    private static class List<T> {

        private Node<T> head;
        private Node<T> tail;

        public boolean isEmpty() {
            return head == null;
        }

        public void add(T value) {

            Node node = new Node(value);

            if (head == null) {
                head = tail = node;
            } else {
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
        }

        public T remove() {
            T value = head.value;
            if (head == tail) {
                head = tail = null;
            } else {
                head.next.prev = null;
                head = head.next;
            }

            return value;
        }

        public void append(List<T> list) {
            if (list.head == null) {
                return;
            }

            if (head == null) {
                head = list.head;
                tail = list.tail;
            } else {
                tail.next = list.head;
                list.head.prev = tail;
                tail = list.tail;
            }

            list.head = null;
            list.tail = null;
        }

        private static class Node<T> {

            T value;
            Node prev;
            Node next;

            public Node(T value) {
                this.value = value;
            }
        }
    }
}
