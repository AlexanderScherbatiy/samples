package structural.proxy;

public class RemoteCalculator {

    Calculator calc = new LocalCalculator();

    public String calculate(String expression) {
        String[] args = expression.split(" ");

        switch (args[0]) {
            case "+":
                int a = Integer.parseInt(args[1]);
                int b = Integer.parseInt(args[2]);
                return Integer.toString(calc.add(a, b));

        }

        return null;
    }
}
