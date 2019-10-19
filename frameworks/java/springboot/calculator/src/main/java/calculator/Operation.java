package calculator;

public enum Operation {
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("*");

    private final String name;

    Operation(String name) {
        this.name = name;
    }

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

    @Override
    public String toString() {
        return name;
    }

    public static Operation fromName(String name) {
        for (Operation op : Operation.values()) {
            if (op.name.equals(name)) {
                return op;
            }
        }
        throw new IllegalArgumentException(String.format("No operation for %s", name));
    }
}
