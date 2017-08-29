package design.flyweight;

import org.junit.Test;

import static org.junit.Assert.*;

public class FlyWeightPatternTest {

    @Test
    public void testBinaryArrayValue() {

        assertArrayEquals(new boolean[]{false}, BinaryArrayFactory.getBinaryArray(0));
        assertArrayEquals(new boolean[]{true}, BinaryArrayFactory.getBinaryArray(1));

        assertArrayEquals(new boolean[]{true, false}, BinaryArrayFactory.getBinaryArray(2));
        assertArrayEquals(new boolean[]{true, true}, BinaryArrayFactory.getBinaryArray(3));

        assertArrayEquals(new boolean[]{true, false, false}, BinaryArrayFactory.getBinaryArray(4));
        assertArrayEquals(new boolean[]{true, false, true}, BinaryArrayFactory.getBinaryArray(5));
        assertArrayEquals(new boolean[]{true, true, false}, BinaryArrayFactory.getBinaryArray(6));
        assertArrayEquals(new boolean[]{true, true, true}, BinaryArrayFactory.getBinaryArray(7));
    }

    @Test
    public void testBinaryArrayCache() {

        int N = 123;
        boolean[] array1 = BinaryArrayFactory.getBinaryArray(N);
        boolean[] array2 = BinaryArrayFactory.getBinaryArray(N);

        assertTrue(array1 == array2);
    }
}
