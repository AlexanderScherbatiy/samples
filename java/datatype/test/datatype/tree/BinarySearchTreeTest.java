package datatype.tree;

import java.util.Arrays;

/**
 * Created by alexsch on 2/3/2017.
 */
public class BinarySearchTreeTest {


    public static void main(String[] args) {

        Integer[] elems = {5, 4, 6, 2, 3, 1, 8, 9, 7};
        Integer[] extraElems = {-1, 12, 10};

        new TestTree<Integer>(elems, extraElems,
                () -> new RecursiveBinarySearchTree<Integer>()).test();
    }

    interface BinarySearchTreeFactory<E extends Comparable<E>> {
        BinarySearchTree<E> createEmptyTree();
    }

    static class TestTree<E extends Comparable<E>> {

        private final E[] elems;
        private final E[] extraElems;

        private final BinarySearchTreeFactory factory;

        public TestTree(E[] elems, E[] extraElems, BinarySearchTreeFactory factory) {
            this.elems = elems;
            this.extraElems = extraElems;
            this.factory = factory;
        }

        void test() {
            BinarySearchTree<E> tree = fillBinaryTree();
            E[] sortedElems = Arrays.copyOf(elems, elems.length);
            Arrays.sort(sortedElems);

            testContains(tree);
            testIterator(tree, sortedElems);
            testStream(tree);
        }

        BinarySearchTree<E> fillBinaryTree() {
            BinarySearchTree<E> tree = factory.createEmptyTree();
            for (E elem : elems) {
                tree.insert(elem);
            }
            return tree;
        }


        void testContains(BinarySearchTree<E> tree) {
            for (E elem : elems) {
                if (!tree.contains(elem)) {
                    throw new RuntimeException(String.format(
                            "Elem %s is not included into tree %s", elem, tree));
                }
            }

            for (E elem : extraElems) {
                if (tree.contains(elem)) {
                    throw new RuntimeException(String.format(
                            "Elem %s is  included into tree %s", elem, tree));
                }
            }
        }

        void testIterator(BinarySearchTree<E> tree, E[] sortedElems) {
            int i = 0;
            for (E elem : tree) {
                if (!sortedElems[i].equals(elem)) {
                    throw new RuntimeException(String.format(
                            "Wrong iterator order step %d, %s instead of %s",
                            i, elem, sortedElems[i]));
                }
                i++;
            }
        }

        void testStream(BinarySearchTree<E> tree) {
            System.out.printf("tree: %s\n", tree);

            tree.parallelStream().forEach((elem) -> {
                System.out.printf("foreach: %d\n", elem);
            });
        }
    }
}
