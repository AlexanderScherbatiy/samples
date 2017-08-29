package structural.proxy;

public class LocalCalculator implements Calculator {

    @Override
    public int add(int a, int b) {
        return a + b;
    }
}
