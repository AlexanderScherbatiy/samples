package structural.composite;

public class ValueExpression implements Expression {

    private final int value;

    public ValueExpression(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
