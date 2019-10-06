package calculator;

import lombok.Data;

@Data
public class Expression {

    private double a;
    private double b;
    private String operation;

    public enum Operation {
        PLUS,
        MINUS,
        MULTIPLY
    }

    public double calculate() {
        return 42;
    }
}
