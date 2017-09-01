package structural.composite;

public class SumExpression implements Expression {
    private final Expression expr1;
    private final Expression expr2;

    public SumExpression(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public int getValue() {
        return expr1.getValue() + expr2.getValue();
    }
}
