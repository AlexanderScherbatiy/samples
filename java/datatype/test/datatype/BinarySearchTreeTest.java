package datatype;

import datatype.tree.BinarySearchTree;

/**
 * Created by alexsch on 2/3/2017.
 */
public class BinarySearchTreeTest {

    public static void main(String[] args) {
        testTree();
        testIterator();
        testStream();
    }

    private static void testTree() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(2);
        tree.insert(3);
        tree.insert(1);

        System.out.printf("tree: %s\n", tree);
    }

    private static void testIterator() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(5);
        tree.insert(6);
        tree.insert(4);

        System.out.printf("tree: %s\n", tree);

        for (Integer value : tree) {
            System.out.printf("iterator next value: %d\n", value);
        }
    }

    private static void testStream() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(5);
        tree.insert(4);
        tree.insert(6);
        tree.insert(2);
        tree.insert(3);
        tree.insert(1);
        tree.insert(8);
        tree.insert(9);
        tree.insert(7);

        System.out.printf("tree: %s\n", tree);

        tree.parallelStream().forEach((elem) -> {
            System.out.printf("foreach: %d\n", elem);
        });
    }
}
