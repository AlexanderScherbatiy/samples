package datatype.signal;

/**
 * Created by alexsch on 1/31/2017.
 */
public class VarSignal<E> extends AbstractSignal<E> {

    private E value;

    public VarSignal(E value) {
        super(false);
        this.value = value;
        set(value);
    }

    public final void set(E value) {
        this.value = value;
        evaluate();
    }

    @Override
    protected E calculate() {
        return value;
    }
}
