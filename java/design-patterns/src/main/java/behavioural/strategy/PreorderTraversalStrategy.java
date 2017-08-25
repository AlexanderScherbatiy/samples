package behavioural.strategy;

import java.util.function.Consumer;

public class PreorderTraversalStrategy<T> implements TreeTraversalStrategy<T> {

    @Override
    public void traverse(Tree<T> tree, Consumer<T> consumer) {
        traverse(tree.getRoot(), consumer);
    }

    public void traverse(TreeNode<T> node, Consumer<T> consumer) {

        if (node != null) {
            consumer.accept(node.getValue());
            traverse(node.getLeft(), consumer);
            traverse(node.getRight(), consumer);
        }
    }
}
