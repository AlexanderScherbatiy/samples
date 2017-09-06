package structural.facade;

import org.junit.Test;
import static org.junit.Assert.*;

public class FacadePatternTest {

    @Test
    public void testFacadePattern() {
        Computer computer = new Computer();
        assertEquals(5, computer.sum(2, 3));
        assertEquals(6, computer.mul(2, 3));
    }

}
