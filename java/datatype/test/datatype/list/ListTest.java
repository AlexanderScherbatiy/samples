package datatype.list;

/**
 * Created by alexsch on 3/9/2017.
 */
public class ListTest {
    public static void main(String[] args) {

        testListHead(new LinkedList<>());
        testListTail(new LinkedList<>());
    }

    private static void testListHead(List<Integer> list) {

        assertTrue(list.isEmpty(), "List is not empty!");
        list.addHead(1);
        list.addHead(2);
        list.addHead(3);
        list.addHead(4);
        list.addHead(5);

        for (int elem : list) {
            System.out.printf("list elem: %d\n", elem);
        }

        assertTrue(!list.isEmpty(), "List is not empty!");
    }

    private static void testListTail(List<Integer> list) {

        assertTrue(list.isEmpty(), "List is not empty!");
        list.addTail(1);
        list.addTail(2);
        list.addTail(3);
        list.addTail(4);
        list.addTail(5);

        for (int elem : list) {
            System.out.printf("list elem: %d\n", elem);
        }

        assertTrue(!list.isEmpty(), "List is not empty!");
    }

    private static void assertTrue(boolean result, String message) {
        if (!result) {
            throw new RuntimeException(message);
        }
    }
}
