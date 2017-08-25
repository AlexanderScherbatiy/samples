package behavioural.strategy;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;


public class StrategyPatternTest {

    @Test
    public void testInorderTraversalStrategy() {

        // root, left, right
        List<String> list = testStrategy(new InorderTraversalStrategy<String>());
        assertEquals(list.size(), 3);
        assertEquals(list.get(0), "1");
        assertEquals(list.get(1), "2");
        assertEquals(list.get(2), "3");
    }

    @Test
    public void testPreorderTraversalStrategy() {

        // root, left, right
        List<String> list = testStrategy(new PreorderTraversalStrategy<String>());
        assertEquals(list.size(), 3);
        assertEquals(list.get(0), "2");
        assertEquals(list.get(1), "1");
        assertEquals(list.get(2), "3");
    }

    @Test
    public void testPostorderTraversalStrategy() {

        // left, right, root
        List<String> list = testStrategy(new PostorderTreeTraversalStrategy<String>());
        assertEquals(list.size(), 3);
        assertEquals(list.get(0), "1");
        assertEquals(list.get(1), "3");
        assertEquals(list.get(2), "2");
    }

    private List<String> testStrategy(TreeTraversalStrategy<String> strategy) {
        List<String> list = new ArrayList<>(3);

        // root 2
        // left 1
        // right 3
        TreeNode<String> left = new TreeNode("1");
        TreeNode<String> right = new TreeNode("3");
        TreeNode<String> root = new TreeNode("2", left, right);

        Tree<String> tree = new Tree<>(root);
        tree.setTraversalStrategy(strategy);
        tree.traverse((elem) -> {
            list.add(elem);
        });

        return list;
    }
}
