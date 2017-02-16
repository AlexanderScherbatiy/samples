package datatype.signal;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by alexsch on 1/31/2017.
 */
public abstract class AbstractSignal<E> {

    private E value;
    private Set<AbstractSignal<E>> children = new HashSet<>();

    public AbstractSignal(AbstractSignal... parents) {
        this(true, parents);
    }

    AbstractSignal(boolean evaluate, AbstractSignal... parents) {
        for (AbstractSignal<E> parent : parents) {
            parent.children.add(this);
        }

        if (evaluate) {
            evaluate();
        }
    }

    public E get() {
        return value;
    }

    protected abstract E calculate();

    protected void evaluate() {
        value = calculate();
        for (AbstractSignal<E> child : children) {
            child.evaluate();
        }
    }

    @Override
    public String toString() {
        return String.format("Signal[%s]", value);
    }
}
