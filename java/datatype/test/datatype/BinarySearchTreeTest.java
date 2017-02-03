package datatype;

import datatype.tree.BinarySearchTree;

/**
 * Created by alexsch on 2/3/2017.
 */
public class BinarySearchTreeTest {

    public static void main(String[] args) {

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(2);
        tree.insert(3);
        tree.insert(1);

        System.out.printf("tree: %s", tree);
    }
}
