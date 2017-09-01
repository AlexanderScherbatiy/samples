package structural.composite;

import org.junit.Test;

import static org.junit.Assert.*;

public class CompositePatternTest {

    @Test
    public void testExpressionTree() {

        Expression value1 = new ValueExpression(1);
        Expression value2 = new ValueExpression(2);
        Expression sum = new SumExpression(value1, value2);

        assertEquals(3, sum.getValue());
    }
}
