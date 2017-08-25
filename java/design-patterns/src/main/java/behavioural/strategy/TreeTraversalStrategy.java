package behavioural.strategy;

import java.util.function.Consumer;

public interface TreeTraversalStrategy<T> {

    void traverse(Tree<T> tree, Consumer<T> consumer);
}
