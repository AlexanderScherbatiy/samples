package behavioural.strategy;

import java.util.function.Consumer;

public class Tree<T> {

    private final TreeNode<T> root;
    private TreeTraversalStrategy<T> traversalStrategy;

    public Tree(TreeNode<T> root) {
        this.root = root;
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    public TreeTraversalStrategy<T> getTraversalStrategy() {
        return traversalStrategy;
    }

    public void setTraversalStrategy(TreeTraversalStrategy<T> traversalStrategy) {
        this.traversalStrategy = traversalStrategy;
    }

    public void traverse(Consumer<T> consumer) {
        getTraversalStrategy().traverse(this, consumer);
    }
}
