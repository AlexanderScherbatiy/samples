package structural.proxy;

public class ProxyCalculator implements Calculator {

    RemoteCalculator remoteCalc = new RemoteCalculator();

    @Override
    public int add(int a, int b) {

        String expr = String.format("+ %d %d", a, b);
        return Integer.parseInt(remoteCalc.calculate(expr));
    }
}
