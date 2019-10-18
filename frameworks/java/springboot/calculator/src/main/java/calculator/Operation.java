package calculator;

public enum Operation {
    PLUS,
    MINUS,
    MULTIPLY;

    public double calculate(double a, double b) {
        switch (this) {
            case PLUS:
                return a + b;
            case MINUS:
                return a - b;
            case MULTIPLY:
                return a * b;
            default:
                throw new RuntimeException("Unknown operation: " + this);
        }
    }
}
