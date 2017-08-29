package structural.proxy;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProxyPatternTest {

    @Test
    public void testLocalCalculator() {

        Calculator calc = new LocalCalculator();
        assertEquals(calc.add(1, 2), 3);

    }

    @Test
    public void testRemoteCalculator() {

        RemoteCalculator calc = new RemoteCalculator();
        assertEquals(calc.calculate("+ 1 2"), "3");

    }

    @Test
    public void testProxyCalculator() {

        Calculator calc = new ProxyCalculator();
        assertEquals(calc.add(1, 2), 3);
    }
}
