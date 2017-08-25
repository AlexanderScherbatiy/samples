package behavioural.strategy;

public class TreeNode<T> {

    private final T value;
    private final TreeNode<T> left;
    private final TreeNode<T> right;

    public TreeNode(T value) {
        this(value, null, null);
    }

    public TreeNode(T value, TreeNode<T> left, TreeNode<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public T getValue() {
        return value;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public TreeNode<T> getRight() {
        return right;
    }
}
